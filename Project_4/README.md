# CDN
### Team Member: Zhongxi Wang, Shiyu Wang

## Approach
This project includes two parts, HTTP and DNS.


### DNS:
In the DNS part, our high level approach is basically the same as the approach we took for Project 4.
We create a class called DNS packet, this is the template we use to generate or parse DNS packets. The class dnsserver
listens on a UDP port for incoming DNS requests. And it also does CDN's job to choose replica based on geolocation.
We use an online geolocation api for this purpose.

Since the geolocation api might respond slowly, we use a thread to query this api in the background. When a client with
a new IP visits DNS server for the first time, we will pick the IP of a random EC2 server and return to it. The thread
will then query the geolocation of this IP. Next time when this client visits again, we have already known its location,
and then we will return to it the IP of the closest EC2 server.

--------------------------------------------------------------------------------------
### HTTP:

For HTTP part, It basically need to do 5 things under different conditions:
1. Accept and parse request from client
2. Check if we have requested content in disk or memory cache. If so, return the content from local cache.
2. Send request to origin and accept response from it.
3. send response to client.
Therefore, our HTTP server is working along this process to firstly listen request from client. Then we check if we have
the requested content in disk or memory. If we have it, return it to client. Otherwise, we request it from origin and
send it to client.

For the disk, we use zlib to compress each http page into a Z file to save space, so we can store about 600 http pages
on disk. And we put their name into a set. Each time when client requests a page, we check if the path matches the file
name in the set. If so, we decompress the http page and send it to client. Similarly, we prepared a hash table to store
300 compressed http pages and it's path into memories. We leave enough memory for processing.
In order to avoid influencing the listening, we build this hash table in another thread.

On the other hand, we use fork() to handle concurrency of http part. Every new client will be put in a new child process
 to avoid long-time waiting.

## Challenge
We meet many challenges.
Getting right with all the fields in DNS packet header is a little tricky.

For Http, we firstly think use shelve or SQLite to store pages. However, they are not safe for multiprocess. Also it
took too much space to store one page. Then we come out to use zlib to directly save compressed file on the disk which
saves us 9/10 spaces. The second challenge is to use multiprocess to handle concurrency. We were getting problems like
unclosed child process, zombie files and other problems. Then we finally solved them by taking care of child process and
 signal library.
Other problems like multithread and php files caching also happend, but we solved them too.

## Evaluation
We evaluate our http part by wget http://[your server name]:[your port name]/[path to content]. With cache, the time of
generating a http page is improved from 0.5s to 0.004s or less.

## Future Work
In the future, we would like to improve maping IPs to nearby replica servers based on both geolocation and active measurements.
