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
package com.intellij.plugins.firstspirit.languagesupport.psi;

import com.intellij.plugins.firstspirit.languagesupport.FirstSpiritOutputChannelLanguage;
import com.intellij.lang.Language;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class FirstSpiritOutputChannelTokenType extends IElementType {
    public FirstSpiritOutputChannelTokenType(@NotNull @NonNls String debugName, @Nullable Language language) {
        super(debugName, language);
    }

    public FirstSpiritOutputChannelTokenType(@NotNull @NonNls String debugName) {
        super(debugName, FirstSpiritOutputChannelLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}