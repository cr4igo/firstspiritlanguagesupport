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

import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.AutoCompletionPolicy;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.plugins.firstspirit.languagesupport.psi.FirstSpiritOutputChannelBody;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

public class FirstSpiritOutputChannelCompletionContributor extends CompletionContributor {

    public FirstSpiritOutputChannelCompletionContributor() {

        extend(CompletionType.BASIC,
                        PlatformPatterns.psiElement().withLanguage(FirstSpiritOutputChannelLanguage.INSTANCE).withParent(FirstSpiritOutputChannelBody.class),
                new CompletionProvider<CompletionParameters>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        resultSet.addElement(LookupElementBuilder.create("$-- comment --$").withIcon(FirstSpiritOutputChannelIcons.TEMPLATE).withInsertHandler(new InsertHandler<LookupElement>() {
                            @Override
                            public void handleInsert(final InsertionContext insertionContext, final LookupElement lookupElement) {
                                // TODO: implement insertion of user comment here
                                insertionContext.setTailOffset(insertionContext.getStartOffset() + 4);
                                //insertionContext.trackOffset(insertionContext.getStartOffset() + 3, true);
                                insertionContext.setAddCompletionChar(true);
                                insertionContext.commitDocument();
                            }
                        }));
                        resultSet.addElement(LookupElementBuilder.create("$CMS_FOR(element,elements)$$CMS_END_FOR$").withIcon(FirstSpiritOutputChannelIcons.TEMPLATE));
                        resultSet.addElement(LookupElementBuilder.create("$CMS_IF(var)$$CMS_ELSE$$CMS_END_IF$").withIcon(FirstSpiritOutputChannelIcons.TEMPLATE));
                        resultSet.addElement(LookupElementBuilder.create("$CMS_IF(var)$$CMS_ELSIF(var)$$CMS_ELSE$$CMS_END_IF$").withIcon(FirstSpiritOutputChannelIcons.TEMPLATE));
                        resultSet.addElement(LookupElementBuilder.create("$CMS_IF(var)$$CMS_END_IF$").withIcon(FirstSpiritOutputChannelIcons.TEMPLATE));
                        resultSet.addElement(LookupElementBuilder.create("$CMS_INCLUDE()$").withIcon(FirstSpiritOutputChannelIcons.TEMPLATE));
                        resultSet.addElement(LookupElementBuilder.create("$CMS_REF(var)$").withIcon(FirstSpiritOutputChannelIcons.TEMPLATE));
                        resultSet.addElement(LookupElementBuilder.create("$CMS_RENDER()$").withIcon(FirstSpiritOutputChannelIcons.TEMPLATE));
                        resultSet.addElement(LookupElementBuilder.create("$CMS_SET(var)$$CMS_END_SET$").withIcon(FirstSpiritOutputChannelIcons.TEMPLATE));
                        resultSet.addElement(LookupElementBuilder.create("$CMS_SWITCH()$$CMS_CASE()$$CMS_END_SWITCH$").withIcon(FirstSpiritOutputChannelIcons.TEMPLATE));
                        resultSet.addElement(LookupElementBuilder.create("$CMS_TRIM(level:3)$ $CMS_END_TRIM$").withIcon(FirstSpiritOutputChannelIcons.TEMPLATE));
                        resultSet.addElement(LookupElementBuilder.create("$CMS_VALUE(var)$").withIcon(FirstSpiritOutputChannelIcons.TEMPLATE).withAutoCompletionPolicy(AutoCompletionPolicy.ALWAYS_AUTOCOMPLETE));
                        resultSet.addElement(LookupElementBuilder.create("$CMS_VALUE(var, default:\"\")$").withIcon(FirstSpiritOutputChannelIcons.TEMPLATE));
                    }
                }
        );

        // TODO: for media references just make a lookup to the list of firstspirit servers and show the completions of unique ids (FirstSpirit uids)
        /*
        extend(CompletionType.BASIC, PlatformPatterns.psiElement().withLanguage(OutputChannelLanguage.INSTANCE),
                new CompletionProvider<CompletionParameters>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        fillMediaUidIntesion(resultSet);
                    }
                }
        );*/



    }


    @Override
    public void fillCompletionVariants(CompletionParameters parameters, CompletionResultSet result) {
        super.fillCompletionVariants(parameters, result);
    }

/*
    final String host = "192.168.178.21";
    final int port = 8000;
    final String user = "Admin";
    final String password = "Admin";

    public void fillMediaUidIntesion(CompletionResultSet resultSet) {

        try {
            Connection con = ConnectionManager.getConnection(host, port, ConnectionManager.HTTP_MODE, user, password);
            con.connect();

            for (Project project : con.getProjects()) {
                MediaStoreRoot mediaStoreRoot = (MediaStoreRoot) project.getUserService().getStore(Store.Type.MEDIASTORE, true);
                Listable<Media> medias = mediaStoreRoot.getChildren(Media.class, true);
                for (Media media : medias.toList()) {
                    resultSet.addElement(LookupElementBuilder.create(media.getUid()).withTypeText(media.getDisplayName(project.getMasterLanguage()) + " - " + project.getName(), true));
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (AuthenticationException e) {
            e.printStackTrace();
        } catch (MaximumNumberOfSessionsExceededException e) {
            e.printStackTrace();
        }
    } */
}
