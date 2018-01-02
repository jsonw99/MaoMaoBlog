### index
the main page including the newest and hottest articles, tags, and users.
* /blogs: GET  
    the list view of the articles.
    * order: the type of the order, new/hot, by default is new.
    * keyword: the keyword to search.
    * async: boolean, synchronous or asynchronous to handle the query.
    * pageIndex
    * pageSize
    
### user space
the main page for one specific user
* /u/{username} : GET  
    the information of the given user.
    * username: username.
* /u/{username}/profile : GET  
    the user profile modification page.
    * username: username.
* /u/{username}/profile : POST  
    save the user profile.
    * username: username.   
    * User: the object to save.
* /u/{username}/avatar : GET  
    return the user avatar.
    * username: username.
* /u/{username}/avatar : POST  
    save the user avatar.
    * username: username.  
* /u/{username}/blogs : GET  
    the articles of the given user.
    * order: the type of the order, new/hot, by default is new.
    * catalog: the classification of the articles, by default is empty.
    * keyword: the keyword to search.
    * async: boolean, synchronous or asynchronous to handle the query.
    * pageIndex
    * pageSize
    * username: username.
* /u/{username}/blogs/edit : GET    
    the page to add a new article.
    * username: username.
* /u/{username}/blogs/edit : POST    
    save a new article.
    * username: username.
    * Blog: the object to save.    
* /u/{username}/blogs/edit/{id} : GET    
    the page to edit one specific article.
    * username: username. 
    * id: id of the article.    
* /u/{username}/blogs/edit/{id} : DELETE    
    delete one specific article.
    * username: username. 
    * id: id of the article.    
    
### login
the page to login.   
* /login : GET 
* /login : POST
    * username
    * password
    * remember-me
    
### register
the page to register new users.
* /register : GET
* /register : POST
    * User: the object to create.

### users
the user management page.
* /users : GET  
the list view of users.
    * async: boolean, synchronous or asynchronous to handle the query.
    * pageIndex
    * pageSize
    * name: the key word to search the user.    
* /users/add : GET  
the page to add new user.
* /users/add : POST  
save the new user.
    * User
    * authorityId: role id.
* /users/{id} : DELETE  
delete the user.
    * id: user id.
* /users/edit/{id} : GET  
edit the user.
    * id: user id.   

### comments
comments management page.
* /comments : GET  
the list of comments for one specific blog.
    * blogId
* /comments/{id} : DELETE
    * id: the id of comments.
    * blogId: the id of blog.

### votes
votes management page.
* /votes : POST
    save the vote for one specific article.
    * blogId
* /votes/{id} : POST
    delete the vote for one specific article.
    * id: vote id.
    * blogId 

### catalogs
the category management.
* /catalogs : GET  
    the category for one specific user.
    * username
* /catalogs : POST  
    save the category for one specific user.
    * username    
    * CatalogVO: including username and Catalog
* /catalogs/edit : GET  
    edit the categories for one specific user.
    * username
* /catalogs/edit/{id} : GET  
    edit the category for one specific user.
    * username
    * id: the id of catalog
* /catalogs/edit/{id} : DELETE  
    edit the category for one specific user.
    * username
    * id: the id of catalog




























