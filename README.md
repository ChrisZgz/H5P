# H5P

On the Drawable package we have all the Images we will use in the application, and in this case
we also have some background to the buttons in XML files

On the Layout package we have all the XML files, which are the "screens"

On the Menu package we have a XML file with the menu that is created on the upper part of the 
screen

We have the Values package which has a XML file and other 2 packages.
The XML file is for the colors that the app use.
The first package "strings" is where we put the string values, for example "CEM4SME Minigame" we 
have it in English and Spanish, and it is like that with all the string values in the app.
The second package "themes" is where we create the backgrounds of the layouts.

And lastly we have the Gradle Scripts; there is only one this that you should check and it is the
build.gradle(Module:app) because from there you can change the API of the app, add dependencies,
change the project name, and more things