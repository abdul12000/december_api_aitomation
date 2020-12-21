Feature: Imagine you are building a social network. Starting from simple functionality. Users are now able to make posts and comment on them.
#  You are working in the backend team that exposes the service: https://jsonplaceholder.typicode.com/ which has the following endpoints:
#
#  1. Make posts: https://jsonplaceholder.typicode.com/posts
#  2. Comment on posts: https://jsonplaceholder.typicode.com/comments
#  3. List of users: https://jsonplaceholder.typicode.com/users

  @regression
  Scenario Outline: Test that existing posts can be retreived with a GET request
    Given service is up and running
    When i search for "<id>" of a post with a GET method
    Then i should get the correct "<id>", "<title>" and "<body>" returned with status code of 200
    Examples:
      | id | title                                                                      | body                                                                                                                                                              |
      | 1  | sunt aut facere repellat provident occaecati excepturi optio reprehenderit | quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto |
      | 5  | nesciunt quas odio                                                         | repudiandae veniam quaerat sunt sed\nalias aut fugiat sit autem sed est\nvoluptatem omnis possimus esse voluptatibus quis\nest aut tenetur dolor neque            |

  @regression
  Scenario Outline: Test that new posts can be created with the POST request
    Given service is up and running
    When I create a new post with the following details "<uId>","<title>" and "<body>",
    Then i should get the correct "<uId>","<title>" and "<body>" returned and with status code of 201
    Examples:
      | uId   | title                | body                                                     |
      | 10008 | My best Holiday ever | I went to Singapore and I had a nice time with my family |