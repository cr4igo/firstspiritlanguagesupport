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

import com.intellij.lang.Language;

public class FirstSpiritOutputChannelLanguage extends Language {
    public static final FirstSpiritOutputChannelLanguage INSTANCE = new FirstSpiritOutputChannelLanguage();

    private FirstSpiritOutputChannelLanguage() {
        super("FirstSpiritOutputChannel");
    }

    @Override
    public boolean isCaseSensitive() {
        return true;
    }
}
