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

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

public class FirstSpiritOutputChannelColorSettingsPage implements ColorSettingsPage {

    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("keyword", FirstSpiritOutputChannelSyntaxHighlighter.FSKEYWORD),
            new AttributesDescriptor("bracket", FirstSpiritOutputChannelSyntaxHighlighter.BRACKET),
            new AttributesDescriptor("bad character", FirstSpiritOutputChannelSyntaxHighlighter.BAD_CHARACTER),
            new AttributesDescriptor("comment", FirstSpiritOutputChannelSyntaxHighlighter.COMMENT),
            new AttributesDescriptor("string", FirstSpiritOutputChannelSyntaxHighlighter.STRING),
            new AttributesDescriptor("normal templating text", FirstSpiritOutputChannelSyntaxHighlighter.NON_FS),
    };

    @Nullable
    @Override
    public Icon getIcon() {
        return FirstSpiritOutputChannelIcons.TEMPLATE;
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new FirstSpiritOutputChannelSyntaxHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return "$-- some block comment\n" +
                "   which has two lines --$\n" +
                " some text comes here" +
                "\n" +
                "$CMS_IF(my_variable_is_true)$ \n" +
                "    $CMS_IF(var1)$ \n" +
                "    $CMS_END_IF$\n" +
                "    $CMS_VALUE(anystring, default:\"foo\")$\n" +
                "    $CMS_VALUE(anystring)$\n" +
                "    $CMS_IF(var2)$ \n" +
                "    $CMS_END_IF$\n" +
                "$CMS_END_IF$";
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }

    @NotNull
    @Override
    public AttributesDescriptor[] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @NotNull
    @Override
    public ColorDescriptor[] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "FirstSpiritOutputChannel";
    }
}
