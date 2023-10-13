# SynalogikTest 
## About
### SynalogikTestApplication provides users with basic statistics about uploaded text files. The statistics are:
1. Total number of words
2. the average word length
3. the most frequently occurring word length
4. list of the number of words of each length

For example, a text file containing the sentence  

*Hello world & good morning. The date is 18/05/2016*  

Will return  

*Word count = 9*  
*Average word length = 4.556*  
*Number of words of each length:*  
*Number of words of length 1 is 1*  
*Number of words of length 2 is 1*  
*Number of words of length 3 is 1*  
*Number of words of length 4 is 2*  
*Number of words of length 5 is 2*  
*Number of words of length 7 is 1*  
*Number of words of length 10 is 1*  
*The most frequently occurring word length is 4, for word lengths of 4 & 5*

### Definition of "word":
1. Alphanumeric characters (letters and digits): Words are considered sequences of letters and digits as they typically appear in natural language. For example, "word123" is counted as a single word.  
2. Ampersand (&): The code treats the ampersand character "&" as part of a word.  
3. Slashes (/) and hyphens (-): The code also treats slashes and hyphens as part of a word. For example, "self-driving" or "18/05/2016" are counted as single words.  
4. Word Separators: The code uses other characters, such as spaces or punctuation marks, as word separators. These characters are not included in the words. For example, "hello, world!" is counted as two words: "hello" and "world."  

### File sizes
The maximum file size is set to 10MB, this can be amended in the application.properties

### Prerequisites
Java17, Maven 3.9.0 , and Postman

## Installation
### Set up SynalogikTestApplication
The first step is to clone the code from the below repository:
https://github.com/herbataszatana/SynalogikTest.git
Second in the terminal run

```
mvn clean install
```
Once the build is successfully completed run

```
mvn spring-boot:run 
```
### Postman configurations
#### Once that's completed go to Postman and reate a new HTTP request
![image](https://github.com/herbataszatana/SynalogikTest/assets/71353315/7513d35e-b020-4c80-a3cf-6371687b8fe5)


#### Update Headers as below

Key: Content-Type
Value: multipart/form-data
Accept: application/json
![image](https://github.com/herbataszatana/SynalogikTest/assets/71353315/4f2e187e-7b9b-427e-aa9f-6c197827d027)

#### Update Body


Key: file (select type to File)
Value: select the file from your device
![image](https://github.com/herbataszatana/SynalogikTest/assets/71353315/13ef7485-7ecb-4a61-ba24-0aff8cdb7324)

#### Once the POST request is created and configured as above. Click on 'SEND' button.
![image](https://github.com/herbataszatana/SynalogikTest/assets/71353315/d246b72a-b454-4fbc-9eb4-d23f125e2da8)


#### The result then will be shown in the body part
![image](https://github.com/herbataszatana/SynalogikTest/assets/71353315/1da61aed-6e9e-4011-82d3-acfd0a50e0c0)
