# Gif_Search
- The main screen contains the toolbar and recyclerView. Toolbar contains menuItem which has actionViewClass="SearchView", 
recyclerView contains CardView's each of which show gif, if you click on it and hold, it will work out ripple effect, if you 
release the gif it will open in an enlarged format.
- After the user searches for a term started new activity which contains: collapsingToolbarLayout with official GiphyAPI icon and title = user query, 
button home and menu; recyclerView same as the first screen. The menu is a mechanism for sorting the received answer according
to the selected rating.
Animation of gif is implemented using the <a href="https://github.com/bumptech/glide">Glide</a> library.
