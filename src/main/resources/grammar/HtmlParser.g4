parser grammar HtmlParser;

//options { }

document
    : element* EOF
    ;

element
    : TAG_OPEN NAME (attribute)* TAG_CLOSE
      (element* TAG_OPEN SLASH NAME TAG_CLOSE | TAG_OPEN SLASH NAME TAG_CLOSE)
    ;

attribute
    : NAME EQUALS STRING
    ;
