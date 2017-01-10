#Title: Measuring Stability using Reflection API
#Date: 1/10/2017
#Name: Shane Gleeson
#Student Number: G00311793
#Lecturer - Dr John Healy

===================================================================================================================

#Overview
---------
One of the most powerful features of the Java language is the ability to dynamically inspect
types at run-time. An application, through the various forms of inheritance and composition
forms an object graph inside a JVM, the structure of which is a realisation of a UML class
diagram. Specifically, the object graph is a digraph (directed graph) where the edges correspond
to the dependencies between classes. As a graph data structure, the standard graph algorithms,
such as depth / breadth first search, topological sorting and layout algorithms can all be applied
to an object graph if necessary.

#Deploying
----------
-Run Java project in Eclipse
-When prompted browse and find your desired Jar file
-Click Calculate Stability
- A pop up window will then appear of InDegree, OutDegree, Stability and the Class Names

