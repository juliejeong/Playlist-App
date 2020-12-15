# Playlist-App

AppDev Hack Challenge Playlist App

Frontend Description:

The frontend aspect of Cadenza has 6 fragments, 3 recycler views, as well as a bottom navigation bar, and a few data classes. 

The 6 fragments & recycler views:
 - FriendsFragment: The fragment that pops up when you want to search up a friend to follow! (uses a recyclerview to display the search results)

- ProfileFragment: This fragment displays all of the user's info such as their name, profile picture, username, friends, and more!

- RecommendFragment: This fragment is used to recommend a song to someone. It is brought onto the screen when the user pressed the recommend button on an item in their feed. Here is where the user can search the song that they want to recommend and add a note to their friend.

- RequestFragment: The fragment that is shown when a user wants to post a request for a certain genre of songs. The user can type in what kind of genre they're looking for as well as a post body.

- FeedFragment: Displays the feed which shows the requests from the users friends. Here, the user can see the otehr responses to the posts or recommend a song to the person who posted the request. (uses a recyclerview to display the feed items)

- ResponseFragment: Displays the comments/recommendations on a post (uses a recyclerview to display the responses on a post)

Application Logic: 
We learned from the Android Lectures that fragments should not directly interact with each other. We must pass data through the Main Activity and the Main Activity passes it to the next fragment. When creating the buttons that, when clicked on, will display a new fragment, we created click listeners in the fragments that created an intent which passed the data to the Main Activity. From there, the Main Activity would pass the data and display the correct fragment using the corresponding methods. In order to make sure that the right fragment would pop up and it wouldn't just be any time putExtra was used, we put in a boolean value that would be put in as true when the button was clicked, that way the corresponding fragment would only display if the boolean value input was true. In addition, we tried to make the app look as close to the figma designs as possible but due to time constraints did not completely.

Networking: To access the data from the backend, we used moshi and JsonAdapter to get necessary data. We first retrieved the entire data (including fields that are visible to users) and stored them in a mutable list. Then, specific fields from the list are put into our recyclerView  fragment which displays the friends list, feed, and requests and responses. (However, there were some issues so we commented out this part & used mock data for the demo).
 


