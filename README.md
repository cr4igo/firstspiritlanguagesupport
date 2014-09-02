# FirstSpirit Language Support
This plugin provides language support for the CMS FirstSpirit&#8482; templating syntax and some features for FirstSpirit&#8482; template developers.
As IDE all of the JetBrains IDEs (IntelliJ IDEA, PyCharm, RubyMine, PhpStorm, AppCode, WebStorm) can make use of this plugin.

![syntax highlighting and simple code completion](https://github.com/cr4igo/firstspiritlanguagesupport/blob/master/misc/images/firstspiritlanguagesample.png)

## TOC
+ [Links](#links)
+ [Installation](#installation)
+ [Features](#features)
+ [Build](#build)

## Links
[FirstSpirit&#8482; Community](https://community.e-spirit.com/groups/firstspirit-languagesupport)

## Installation
You can download the releases here: https://github.com/cr4igo/firstspiritlanguagesupport/releases .
After downloading the JAR-file you can download the plugin in your Jetbrains IDE manually by selecting the file via
'File' -> 'Settings' -> 'Plugins' -> 'Install plugin from disk...'.

## Features
.. tbd ..

## Build
As a prerequisite you need - as for other language plugins - to install some developer plugins. See the [JetBrains tutorial](http://confluence.jetbrains.com/display/IDEADEV/Developing+Custom+Language+Plugins+for+IntelliJ+IDEA#DevelopingCustomLanguagePluginsforIntelliJIDEA-ImplementingaLexer) or http://blog.jetbrains.com/idea/2013/01/how-to-write-custom-language-support-plugins/.
<br> In short install the following plugins and configure them:
 - JFlex Support
 - Grammar-Kit
 - PsiViewer

1. Checkout this git repository

2. Within IntelliJ IDEA: "File" -> "Open..." and select the target folder you checked out beforehand

3. Now you have to open the context-menu on the "FirstSpiritOutputChannel.bnf" file and call "Generate Parser Code" and "Generate JFlex Lexer".
   After the "Generate Parser Code" the folder "gen" will be created. Please generate within the "gen" folder also the JFlex Lexer file by selecting the "gen\com\intellij\plugins\firstspirit\languagesupport" folder.
   Within the folder "gen\com\intellij\plugins\firstspirit\languagesupport" there should be a file generated named "_FirstSpiritOutputChannelLexer.flex". Now run the JFlex Generator by using the context-menu onto the file and selecting "Run JFlex Generator".
   Please keep in mind: The generated files should never be checked in - thats why those are located under the "gen" folder.
   
4. Now you can compile the code. If you haven't setupped the "IntelliJ Platform Plugin SDK" you have to do so. To "install" and "run" the plugin locally you have to create a new "Plugin"-run-configuration.