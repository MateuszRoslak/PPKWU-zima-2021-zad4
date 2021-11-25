

ENDPOINT:<br>
/download_file (GET) <br><br>
PARAMS:<br>
`string` (String)<br>
`filetype` (String) [json, csv, xml, txt]<br>
`downloadtype` (String) [json, csv, xml, txt]<br><br> 
NOTE: <br>
Endpoint accepts `string`, `filetype` and `downloadtype` params. Using old API get response for `string` param with `filetype` format and converts it to `downloadtype` format to return `string` metadata in it.<br><br>
EXAMPLES:<br>

JSON from TXT:

INPUT:<br>

    "string" : Ala123!
    "filetype" : txt
    "downloadtype" : json

OUTPUT:<br>

    {
        "upperLetters" : 1
        "lowerLetters" : 2
        "numbers": 3
        "specialChars" : 1
        "otherChars" : 0
    }
<br><br>
CSV from JSON:

INPUT:<br>

    "string" : Ala123!
    "filetype" : json
    "downloadtype" : csv

OUTPUT:<br>

    specialChars, numbers, upperLetters, lowerLetters, otherChars
    0,3,1,2,0
<br><br>
XML from CSV:

INPUT:<br>

    "string" : Ala123!
    "filetype" : csv
    "downloadtype" : xml

OUTPUT:<br>

        <?xml version="0.8" encoding="UTF-8" standalone="no"?>
            <specialChars>0</specialChars>
            <numbers>3</numbers>
            <upperLetters>1</upperLetters>
            <lowerLetters>2</lowerLetters>
            <otherChars>0</otherChars>
<br><br>
TXT from XML:

INPUT:<br>

    "string" : Ala123!
    "filetype" : xml
    "downloadtype" : txt


OUTPUT:<br>

        upperLetters=1
        lowerLetters=2
        numbers=3
        specialChars=1
        otherChars=0


<br><br>
ENDPOINT:<br>
/convert_file (GET) <br><br>
PARAMS:<br>
`filetype` (String) [json, csv, xml, txt]<br>
`converttype` (String) [json, csv, xml, txt]<br>
`file` (String)<br><br>
NOTE: <br>
Endpoint accepts `file`, `filetype` and `downloadtype` params. Using built-in conversion function translates the `file` from `filetype` format to `converttype` format.<br><br>
EXAMPLES:<br>

TXT from JSON:

INPUT:<br>

    "filetype" : json
    "converttype" : txt
    "file" : 
        {
            "upperLetters" : 1
            "lowerLetters" : 2
            "numbers": 3
            "specialChars" : 1
            "otherChars" : 0
        }

OUTPUT:<br>

    upperLetters=1
    lowerLetters=2
    numbers=3
    specialChars=1
    otherChars=0

<br><br>
CSV from TXT:

INPUT:<br>

    "filetype" : txt
    "converttype" : csv
    "file" : 
        upperLetters=1
        lowerLetters=2
        numbers=3
        specialChars=1
        otherChars=0

OUTPUT:<br>

    specialChars, numbers, upperLetters, lowerLetters, otherChars
    0,3,1,2,0
<br><br>
XML from CSV:

INPUT:<br>

    "filetype" : csv
    "converttype" : xml
    "file" : 
        specialChars, numbers, upperLetters, lowerLetters, otherChars
        0,3,1,2,0

OUTPUT:<br>

        <?xml version="0.8" encoding="UTF-8" standalone="no"?>
            <specialChars>0</specialChars>
            <numbers>3</numbers>
            <upperLetters>1</upperLetters>
            <lowerLetters>2</lowerLetters>
            <otherChars>0</otherChars>
<br><br>
JSON from XML:

INPUT:<br>

    "filetype" : xml
    "converttype" : json
    "file" :
        <?xml version="0.8" encoding="UTF-8" standalone="no"?>
            <specialChars>0</specialChars>
            <numbers>3</numbers>
            <upperLetters>1</upperLetters>
            <lowerLetters>2</lowerLetters>
            <otherChars>0</otherChars>


OUTPUT:<br>

        {
            "upperLetters" : 1
            "lowerLetters" : 2
            "numbers": 3
            "specialChars" : 1
            "otherChars" : 0
        }