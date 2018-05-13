/**
 * @license Copyright (c) 2003-2018, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see https://ckeditor.com/legal/ckeditor-oss-license
 */



CKEDITOR.editorConfig = function( config ) {
    config.toolbarGroups = [
        { name: 'styles', groups: [ 'styles' ] },
        { name: 'clipboard', groups: [ 'clipboard', 'undo' ] },
        { name: 'editing', groups: [ 'find', 'selection', 'spellchecker', 'editing' ] },
        { name: 'forms', groups: [ 'forms' ] },
        { name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
        '/',
        { name: 'document', groups: [ 'mode', 'document', 'doctools' ] },
        { name: 'paragraph', groups: [ 'list', 'indent', 'blocks', 'align', 'bidi', 'paragraph' ] },
        { name: 'links', groups: [ 'links' ] },
        { name: 'insert', groups: [ 'insert' ] },
        { name: 'colors', groups: [ 'colors' ] },
        { name: 'tools', groups: [ 'tools' ] },
        { name: 'others', groups: [ 'others' ] },
        { name: 'about', groups: [ 'about' ] }
    ];
    config.language = 'ko';
    // config.uiColor = '#AADC6E';

    config.removeButtons = 'CopyFormatting,RemoveFormat,NewPage,Save,Templates,Source,Preview,Print,Undo,Redo,Copy,Paste,PasteText,PasteFromWord,Replace,Find,SelectAll,Scayt,Form,Checkbox,Radio,Textarea,TextField,Select,Button,ImageButton,HiddenField,CreateDiv,BidiLtr,BidiRtl,Language,Anchor,Image,Flash,Table,HorizontalRule,Smiley,SpecialChar,PageBreak,Iframe,Maximize,About,ShowBlocks,JustifyLeft,JustifyCenter,JustifyRight,JustifyBlock,Styles,Font,FontSize,Format,NumberedList,BulletedList,Indent,Outdent,Blockquote,Unlink,Link,BGColor,TextColor,Superscript,Subscript,Cut';

};

