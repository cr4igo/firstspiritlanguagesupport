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

import com.intellij.lang.BracePair;
import com.intellij.lang.PairedBraceMatcher;
import com.intellij.plugins.firstspirit.languagesupport.psi.FirstSpiritOutputChannelTypes;
import com.intellij.psi.*;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FirstSpiritOutputChannelPairedBraceMatcher implements PairedBraceMatcher {

    private static final BracePair[] BRACE_PAIRS = new BracePair[]{
            // '(' and ( ')$' or ')' )
            new BracePair(FirstSpiritOutputChannelTypes.FSOUT_PAR_LEFT, FirstSpiritOutputChannelTypes.FSOUT_PAR_RIGHT, false),
            new BracePair(FirstSpiritOutputChannelTypes.FSOUT_PAR_LEFT, FirstSpiritOutputChannelTypes.FSOUT_PAR_RIGHT_DOLLAR, false),

            // all FirstSpirit group-expressions of '$CMS_' with '_END_'
            new BracePair(FirstSpiritOutputChannelTypes.FSOUT_IF, FirstSpiritOutputChannelTypes.FSOUT_IF_END, true),
            //TODO: add cases for cms_elsif
            new BracePair(FirstSpiritOutputChannelTypes.FSOUT_FOR, FirstSpiritOutputChannelTypes.FSOUT_FOR_END, true),
            new BracePair(FirstSpiritOutputChannelTypes.FSOUT_SET, FirstSpiritOutputChannelTypes.FSOUT_SET_END, true),
            new BracePair(FirstSpiritOutputChannelTypes.FSOUT_SWITCH, FirstSpiritOutputChannelTypes.FSOUT_SWITCH_END, true),
            new BracePair(FirstSpiritOutputChannelTypes.FSOUT_TRIM, FirstSpiritOutputChannelTypes.FSOUT_TRIM_END, true),
    };

    @Override
    public BracePair[] getPairs() {
        return BRACE_PAIRS;
    }

    @Override
    public boolean isPairedBracesAllowedBeforeType(@NotNull IElementType type1, @Nullable IElementType type2) {
        return true;
    }

    @Override
    public int getCodeConstructStart(PsiFile paramPsiFile, int paramInt) {
        return paramInt;
    }

}
