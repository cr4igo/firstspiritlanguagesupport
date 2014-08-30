/*
* Copyright 2014 Markus Priegl
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/
package com.intellij.plugins.firstspirit.languagesupport;

import com.intellij.util.lang.UrlClassLoader;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import sun.misc.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLEncoder;

public class FirstSpiritClassPathHack {
    private static final Class[] parameters = new Class[]{URL.class};

    public void addFile(UrlClassLoader classLoader, File f) throws Exception {
        System.out.println("Trying to add file " + (f) + " to classpath.");
        addURL(classLoader, f.toURI().toURL());
    }

    public void addURL(UrlClassLoader classLoader, URL u) throws Exception {

        if (classLoader != null) {
            System.out.println("Classloader loaded successfully: " + classLoader);
        } else {
            System.err.println("Cannot load system classloader!");
        }
        Class sysclass = UrlClassLoader.class;

        try {
            Method method = sysclass.getDeclaredMethod("addURL", parameters);
            method.setAccessible(true);
            method.invoke(classLoader, new Object[]{u});
        } catch (Exception e) {
            System.err.println("Error, could not add URL " + u.getPath() + " to classloader!");
            e.printStackTrace();
            throw e;
        }

    }

    public void addFirstSpiritClientJar(UrlClassLoader classLoader, HttpScheme schema, String serverName, int port, String firstSpiritUserName, String firstSpiritUserPassword) throws Exception {
        System.out.println("starting to download fs-client.jar");

        String resolvalbleAddress = null;

        // asking DNS for IP Address, if some error occur choose the given value from user
        try {
            InetAddress address = InetAddress.getByName(serverName);
            resolvalbleAddress = address.getHostAddress();
            System.out.println("Resolved address: " + resolvalbleAddress);
        } catch (Exception e) {
            System.err.println("DNS cannot resolve address, using your given value: " + serverName);
            resolvalbleAddress = serverName;
        }

        String uri = schema + "://" + resolvalbleAddress + ":" + port + "/clientjar/fs-client.jar";
        String versionUri = schema + "://" + resolvalbleAddress + ":" + port + "/version.txt";
        String tempDirectory = System.getProperty("java.io.tmpdir");


        System.out.println(uri);
        System.out.println(versionUri);

        HttpClient client = new HttpClient();
        HttpMethod getVersion = new GetMethod(versionUri);
        client.executeMethod(getVersion);
        String currentServerVersionString = getVersion.getResponseBodyAsString();
        System.out.println("FirstSpirit server you want to connect to is at version: " + currentServerVersionString);

        File fsClientJar = new File(tempDirectory, "fs-client-" + currentServerVersionString + ".jar");

        if (!fsClientJar.exists()) {
            // get an authentication cookie from FirstSpirit
            HttpMethod post = new PostMethod(uri);
            post.setQueryString("login.user=" + URLEncoder.encode(firstSpiritUserName, "UTF-8") + "&login.password=" + URLEncoder.encode(firstSpiritUserPassword, "UTF-8") + "&login=webnonsso");
            client.executeMethod(post);
            String setCookieJsession = post.getResponseHeader("Set-Cookie").getValue();

            // download the fs-client.jar by using the authentication cookie
            HttpMethod get = new GetMethod(uri);
            get.getParams().setCookiePolicy(CookiePolicy.IGNORE_COOKIES);
            get.setRequestHeader("Cookie", setCookieJsession);
            client.executeMethod(get);

            InputStream inputStream = get.getResponseBodyAsStream();
            FileOutputStream outputStream = new FileOutputStream(fsClientJar);
            outputStream.write(IOUtils.readFully(inputStream, -1, false));
            outputStream.close();
            System.out.println("tempfile of fs-client.jar created within path: " + fsClientJar);
        }

        addFile(classLoader, fsClientJar);
    }

    public enum HttpScheme {
        HTTP("http"), HTTPS("https");

        private String schema = null;

        HttpScheme(String schema) {
            this.schema = schema;
        }

        public String toString() {
            return this.schema;
        }
    }
}