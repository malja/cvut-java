# Git

Process this task via the web interface at URL = https: //github.com
Agree with each other and divide into pairs (e.g. Adam and Bob).
Both of then create their own GitHub account.
Then the pair creates a common project, into which a text file is inserted:
Then both students edit their copy of the file on their computers at the same time. Student Adam deletes the "beta" line from the file, and Student Bob inserts the line "12345" between "gamma" and "delta".
Finally, both versions merge on GitHub.

## Note

Since I wanted to do this taks on my own, I used Github's web UI as the second student.

## Required steps

After I removed text "beta" on the web and tried to push my local file with added "12345", I got an message informing me, that there is a new version of the file online. So I was missing some commits in my local repository.
I had to `fetch` changes, then pull origin from the GitHub online repository. This brough changes made online to my local repository. Git could not make merge automatically, and I had to open my text editor to do the changes. After that, It was simple. Just `commit` and `push origin`.

