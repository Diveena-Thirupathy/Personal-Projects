//Listen to form submit
document.getElementById('myform').addEventListener('submit', saveBookmark);

//save bookmark
function saveBookmark(e)
{
  var siteName = document.getElementById('siteName').value;
  var siteUrl = document.getElementById('siteUrl').value;
  if(!validateForm(siteName, siteUrl)){
    return false;
  }
  var bookmark = {
    name: siteName,
    url: siteUrl
  }

/*
  localStorage.setItem('test', 'Hello World');
  console.log(localStorage.getItem('test'));
  localStorage.removeItem('test');
  console.log(localStorage.getItem('test')); */

if(localStorage.getItem('bookmarks') === null){ //Test if bookmarks is null
  var bookmarks =[]; //Initialize array
  bookmarks.push(bookmark); //Add to array
  localStorage.setItem('bookmarks', JSON.stringify(bookmarks)); //wrappin in JSON array will turn it into string from array
} else{
  //fetch from localStorage
  var bookmarks = JSON.parse(localStorage.getItem('bookmarks'));
  //Add bookmark to array
  bookmarks.push(bookmark);
  //Reset back to localStorage
  localStorage.setItem('bookmarks', JSON.stringify(bookmarks));
}
fetchBookmarks();

  e.preventDefault(); //Prevent form from submitting
}

//clear form
document.getElementById('myform').reset();

//function deleteBookmark
function deleteBookmark(){
  var bookmarks = JSON.parse(localStorage.getItem('bookmarks')); //get bookmark from localStorage
  for(var i = 0; i < bookmarks.length; i++){
    if(bookmarks[i].url == url){
      bookmarks.splice(i, 1);
    }
  }
  localStorage.setItem('bookmarks', JSON.stringify(bookmarks));
  fetchBookmarks(); //Re-fetch bookmarks
}
//function to fetch bookmarks
function fetchBookmarks(){
  var bookmarks = JSON.parse(localStorage.getItem('bookmarks'));
  var bookmarksResults = document.getElementById('bookmarksResults');

  //build output
  bookmarksResults.innerHTML = "";
  for(var i = 0; i < bookmarks.length; i++){
    var name = bookmarks[i].name;
    var url = bookmarks[i].url;

    bookmarksResults.innerHTML += '<div class ="well">' +
                                    ' <h4> '+name+
                                    ' <a class="btn btn-default" target="_blank" href="'+url+'"> Visit </a> '+
                                    ' <a onclick="deleteBookmark(\''+url+'\')" class="btn btn-danger" href="#">Delete</a> ' +
                                    '<h4>'+
                                    '<div>';
  }
}

function validateForm(siteName, siteUrl){
  if(!siteName || !siteUrl){
    alert('Please fill in the form');
    return false;
  }

  var expression = /[-a-zA-Z0-9@:%_\+.~#?&//=]{2,256}\.[a-z]{2,4}\b(\/[-a-zA-Z0-9@:%_\+.~#?&//=]*)?/gi;
  var regex = new RegExp(expression);
  if(!siteUrl.match(regex)){
    alert('Please enter a valid URL');
    return false;
  }
  return true;
}
