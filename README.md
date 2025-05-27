# SurveyAndPolling-API

# Introduction
This Survey & Polling API is a service built with Java Spring Boot and H2 Database. It allows users to create surveys, manage questions and options, collect responses, and analyze results.

# Base URL
http://localhost:8081

# Endpoints

SurveyController (/api/surveys):
1. GET /api/surveys - Gets all surveys
2. GET /api/surveys/{id} - Get survey by ID
3. POST /api/surveys - Create a new survey
4. PUT /api/surveys/{id} - Update an existing survey
5. DELETE /api/surveys/{id} - Delete a survey


QuestionController (/api/questions):
1. GET /api/questions - Get all questions
2. GET /api/questions/{id} - Get question by ID
3. POST /api/questions - Create question
4. PUT /api/questions/{id} - Update question
5. DELETE /api/questions/{id} - Delete question

OptionController (/api/options):
1. GET /api/options - Get all options
2. GET /api/options/{id} - Get option by ID
3. POST /api/options - Create option
4. PUT /api/options/{id} - Update option
5. DELETE /api/options/{id} - Delete option

ResponseController (/api/responses):
1. GET /api/responses - Get all responses
2. GET /api/responses/{id} - Get response by ID
3. POST /api/responses - Create response
4. PUT /api/responses/{id} - Update response
5. DELETE /api/responses/{id} - Delete response

ReportController (/api/reports):
1. GET /api/reports/survey-summary - Get survey summary
2. GET /api/reports/audit-logs - Get audit logs