# coder
single-threaded and multi-threaded file encoding
<hr>
The program can encrypt/decrypt a text file (* .txt)

Encryption / decryption is performed using XOR.
<hr>
The key for decryption is stored inside the encrypted file in an arbitrary position (except for the beginning and end of the file).
Position choose at your discretion. The length of the key is also at its discretion.

The original file is not overwritten.
A new file is created with the original name, adding to the name “_encoded”.
For decrypted - add to the name “_decoded”.

Encoding / decoding is one method for both operations.
Parameters = source file, file for result, flag encoding or decoding
(to connect the correct piece of code, for example, to extract the key from the encoded file).
file creation is a separate method (one),
takes the file name and postfix (encoded / decoded) as a parameter.

Added the ability to encrypt a file as single-threaded or multi-threaded.
