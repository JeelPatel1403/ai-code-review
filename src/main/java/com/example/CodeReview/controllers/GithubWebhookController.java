package com.example.CodeReview.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("/api/github")
public class GithubWebhookController {

    /**
     * Handles incoming GitHub webhook events and processes pull request events specifically.
     * @example
     * ResponseEntity<String> response = new GithubWebhookController().handleWebhook("pull_request", "{...}");
     * System.out.println(response.getBody()); // "Webhook Received"
     * @param {String} event - The type of GitHub event sent in the request header, e.g., "pull_request".
     * @param {String} payload - The JSON payload sent in the body of the request containing event details.
     * @return {ResponseEntity<String>} - A ResponseEntity indicating the webhook has been received successfully.
     */
    @PostMapping("/webhook")
    public ResponseEntity<String> handleWehbook(@RequestHeader("X-Github-Event") String event,
            @RequestBody String payload) {

        System.out.println("Received event :" + event);
        JSONObject json = new JSONObject(payload);

        if ("pull_request".equals(event)) {
            String action = json.getString("action");
            if ("opened".equals(action) || "synchronize".equals(action)) {
                String repo = json.getJSONObject("repository").getString("full_name");
                int prNumber = json.getJSONObject("pull_request").getInt("number");
            }
        }

        return ResponseEntity.ok("Webhook Received");
    }

}
