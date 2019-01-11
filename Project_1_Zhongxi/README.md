## Approach
The approach for this project is simple. I first use TCP to make a connection to the server. Afterwards, the returned
message is parsed using Regex. The result of the calculation is then sent back. The whole process is in a loop. The loop
does not stop until the secret flag is received.

## Challenges
The biggest challenge is figuring out Python and Regex. Python is not my base language, but I want to use these projects
 to get myself familiar with this increasingly popular language.

## Tests
The main tests is running the program to see if the secret flags are returned. Other than that, I did a few unittests to
test out the methods which handle Regex. Those methods which handle TCP connections are not tested, as such tests are
beyond unit tests.
