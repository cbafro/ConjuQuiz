# Use verbecc for French conjugations

**Status:** Accepted
**Date:** 2026-11-11

## Context
We need an API that gives us French verb conjugations so the app can check quiz answers and show conjugation tables.

The API we put in our proposal (My French) didn't work. Our spike found it's blocked by Cloudflare, so every request from the app came back with a 403, and their other endpoint was dead. We also looked at a few other options:
- **Verbix:** only works as a website widget, not a proper JSON API
- **danielbscholtz's Flask API:** just source code, needs MongoDB and logins to work
- **Zyla Labs:** works, but it's paid after a short free trial
- **verbecc:** free, open source, returns JSON, and runs in Docker with no setup. The only catch is the public demo is down, so we'd have to run it ourselves.

## Decision
We're going with verbecc and running it ourselves in Docker. The app will call it with Retrofit, and on the emulator it connects to our computer at `10.0.2.2`.

## Consequences
- No API keys, limits or costs, and we don't have to worry about someone else's server going down before the demo
- The server has to be running for the app to fetch new verbs, but anything already loaded is saved in Room so it still works offline
- Testing on a real phone means we'd need to host it somewhere or be on the same Wi-Fi
- The answers come back with pronouns ("nous parlons"), so we'll have to strip those out when checking answers
