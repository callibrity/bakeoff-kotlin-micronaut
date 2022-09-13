Feature: As a user, I want to get a list of all artists

  Background:
    Given an endpoint of "/api/artists"

  Scenario: A list of all artists are returned successfully
    When the GET request is executed
    Then the response status code is 200
    Then the response body is "artists.all.json"