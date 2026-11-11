# Spike 1: French conju Api

## Question
Can our app fetch French conjugations from the api we chose in the proposal??

## What was done
- Read the apis readme from their github, says that the api has no auth and is free.
- Built a logging app that calls the api with different verbs
- Did the same calls using curl in my terminal
- Tried their own website endpoint to see if it worked
- Tried an alternative to the api we're using (verbe.cc)

## What was found
- Every single retrofit request came back with error 403
- Curl also returned 403 on every request. These are cloudflare responses blocking bots
- The website method returned literally nothing (error HTTP 000)
- Verbe.cc's server is up but it returns 404 on its routes

## Log output example

--------------------
SPIKE E parler failed: HTTP 403
SPIKE E etre failed: HTTP 403
SPIKE E aller failed: HTTP 403
SPIKE E preferer failed: HTTP 403
SPIKE E farterblotch failed: HTTP 403
--------------------
didnt want to do the accents on the words


## What does this mean moving forward?
- Can't use the api in the proposal unless we wanna wait for the key request via email, but it ain't certain
- Need another source of conjugation, from another api service or self hosted api
1. Request api through email
2. Self host the open source verbecc-svc
3. Use a paid api (unlikely)

Choice will be put inside the ADR
