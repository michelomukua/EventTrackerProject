# EventTracker REST API

# Overview

  Event Tracker' is a broad term for anything that keeps track of information over time. Examples of these applications are 'Mint' (financial tracking) and 'MyFitnessPal' (diet and exercise tracker). These are very involved applications with a huge feature set.


### Endpoints

| HTTP Verb | URI             | Request Body | Response Body | Status |
|-----------|-----------------|--------------|---------------|---------|
| GET       | `/api/dives`    |              | List of dives | 200   |
| GET       | `/api/dives/17` |              | Single dive   | 200 or 404 |
| POST      | `/api/dives`    | JSON of new dive       | JSON of created dive | 201 or 400 |
| PUT       | `/api/dives/17` | JSON for updating dive | JSON of updated dive | 200, 404, or 400 |
| DELETE    | `/api/dives/17` |              | | 204, 404, or 400 |
