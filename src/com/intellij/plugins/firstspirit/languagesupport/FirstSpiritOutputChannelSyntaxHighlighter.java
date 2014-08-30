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

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import com.intellij.plugins.firstspirit.languagesupport.psi.FirstSpiritOutputChannelTypes;
import org.jetbrains.annotations.NotNull;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class FirstSpiritOutputChannelSyntaxHighlighter extends SyntaxHighlighterBase {
    public static final TextAttributesKey FSKEYWORD = createTextAttributesKey("FIRSTSPIRIT_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey NON_FS = createTextAttributesKey("NON_FIRSTSPIRIT");
    public static final TextAttributesKey STRING = createTextAttributesKey("FIRSTSPIRIT_STRING", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey COMMENT = createTextAttributesKey("FIRSTSPIRIT_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT);
    public static final TextAttributesKey BRACKET = createTextAttributesKey("FIRSTSPIRIT_BRACKET", DefaultLanguageHighlighterColors.BRACKETS);

    public static final TextAttributesKey BAD_CHARACTER = createTextAttributesKey("FIRSTSPIRIT_BAD_CHARACTER");

    private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHARACTER};
    private static final TextAttributesKey[] KEYWORDS = new TextAttributesKey[]{FSKEYWORD};
    private static final TextAttributesKey[] STRING_KEYS = new TextAttributesKey[]{STRING};
    private static final TextAttributesKey[] NON_FS_KEYS = new TextAttributesKey[]{NON_FS};

    private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT};
    private static final TextAttributesKey[] BRACKET_KEYS = new TextAttributesKey[]{BRACKET};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new FirstSpiritOutputChannelLexer();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {

        if (tokenType.equals(FirstSpiritOutputChannelTypes.FSOUT_STRING)) {
            return STRING_KEYS;
        } else if (tokenType.equals(FirstSpiritOutputChannelTypes.FSOUT_SOMETHING)) {
            return NON_FS_KEYS;
        } else if (tokenType.equals(FirstSpiritOutputChannelTypes.FSOUT_COMMENT)) {
            return COMMENT_KEYS;
        } else if (tokenType.equals(FirstSpiritOutputChannelTypes.FSOUT_PAR_LEFT)
                || tokenType.equals(FirstSpiritOutputChannelTypes.FSOUT_PAR_RIGHT)) {
            return BRACKET_KEYS;
        } else if (tokenType.equals(FirstSpiritOutputChannelTypes.FSOUT_IF)
                || tokenType.equals(FirstSpiritOutputChannelTypes.FSOUT_IF_ELSE)
                || tokenType.equals(FirstSpiritOutputChannelTypes.FSOUT_IF_END)
                || tokenType.equals(FirstSpiritOutputChannelTypes.FSOUT_SET)
                || tokenType.equals(FirstSpiritOutputChannelTypes.FSOUT_SET_END)
                || tokenType.equals(FirstSpiritOutputChannelTypes.FSOUT_VALUE)
                || tokenType.equals(FirstSpiritOutputChannelTypes.FSOUT_FOR)
                || tokenType.equals(FirstSpiritOutputChannelTypes.FSOUT_FOR_END)
                || tokenType.equals(FirstSpiritOutputChannelTypes.FSOUT_CASE)
                || tokenType.equals(FirstSpiritOutputChannelTypes.FSOUT_INCLUDE)
                || tokenType.equals(FirstSpiritOutputChannelTypes.FSOUT_RENDER)
                || tokenType.equals(FirstSpiritOutputChannelTypes.FSOUT_SWITCH)
                || tokenType.equals(FirstSpiritOutputChannelTypes.FSOUT_SWITCH_END)
                || tokenType.equals(FirstSpiritOutputChannelTypes.FSOUT_TRIM)
                || tokenType.equals(FirstSpiritOutputChannelTypes.FSOUT_TRIM_END)
                || tokenType.equals(FirstSpiritOutputChannelTypes.FSOUT_TRIM_LEVEL)
                || tokenType.equals(FirstSpiritOutputChannelTypes.FSOUT_PAR_RIGHT_DOLLAR)
                || tokenType.equals(FirstSpiritOutputChannelTypes.FSOUT_DEFAULT)
                || tokenType.equals(FirstSpiritOutputChannelTypes.FSOUT_MEDIA)
                || tokenType.equals(FirstSpiritOutputChannelTypes.FSOUT_SCRIPT)
                ) {
            return KEYWORDS;
        } else if (tokenType.equals(TokenType.BAD_CHARACTER)) {
            return BAD_CHAR_KEYS;
        } else {
            return EMPTY_KEYS;
        }
    }
}
