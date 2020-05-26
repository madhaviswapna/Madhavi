Feature: Application Login


Scenario: Home page default Login
Given User on Landing page
When User Login into Application with uid and pwd
Then Home page is populated
And Cards are displayed