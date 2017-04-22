/******************************************************************************
 *  Name: Michael Dey, Jared Edwards      
 *  NetID:     
 *  Precept:    
 *
 *  Partner Name: Jared Edwards, Michael Dey      
 *  Partner NetID:      
 *  Partner Precept:    
 *
 *  Hours to complete assignment (optional):
 *
 ******************************************************************************/


/******************************************************************************
 *  Describe concisely the data structure(s) you used to store the 
 *  information in synsets.txt. Why did you make this choice?
 *****************************************************************************/

We used two symbol trees, which we created concurrently in order to better find ancestors.

/******************************************************************************
 *  Describe concisely the data structure(s) you used to store the 
 *  information in hypernyms.txt. Why did you make this choice?
 *****************************************************************************/

We used a digraph to store the individual integers in each line.
The first integer was as a Vertex.
The remainding integers in the line were used to create edges.

/******************************************************************************
 *  Describe concisely the algorithm you use in the constructor of
 *  ShortestCommonAncestor to check if the digraph is a rooted DAG.
 *  What is the order of growth of the worst-case running times of
 *  your algorithms as a function of the number of vertices V and the
 *  number of edges E in the digraph?
 *****************************************************************************/

Description:

We used a DirectedCycle from algs4 to test if is acyclic.
We kept a count of roots with outDegree == 0.
If root count == 1, then isRootedDAG == true.

Order of growth of running time:
each vertex is checked once == linear. (proportional to V)

/******************************************************************************
 *  Describe concisely your algorithm to compute the shortest common
 *  ancestor in ShortestCommonAncestor. What is the order of growth of
 *  the running time of your methods as a function of the number of
 *  vertices V and the number of edges E in the digraph? What is the
 *  order of growth of the best-case running time?
 *
 *  If you use hashing, you should assume the uniform hashing assumption
 *  so that put() and get() take constant time.
 *
 *  Be careful! If you use a BreadthFirstDirectedPaths object, don't
 *  forget to count the time needed to initialize the marked[],
 *  edgeTo[], and distTo[] arrays.
 *****************************************************************************/

Description: 
we used DepthFirstDirectedPaths object
we calculated the path to the root.
We created an iterable of ancestors from the first vertex.
We created a BST of the ancestors from the second vertex.
We iterated through the queue, and searched the BST for an occurance of the item in the iterable.
If an item was found, that is the common ancestor.

The depthFirstDirectedPaths = V + E (we do this once for both vertexes)
To create the iterable = V
to create the BST = V
To search the iterable = V
To search the BST = log V



                                              running time
method                               best case            worst case
------------------------------------------------------------------------
length(int v, int w)				O(V + E)				O(V + E)

ancestor(int v, int w)				V + V				2(V + E) + Log V

length(Iterable<Integer> v,			E + V^2				E + V^2
       Iterable<Integer> w)

ancestor(Iterable<Integer> v,		E + V^2				E + V^2	
         Iterable<Integer> w)




/******************************************************************************
 *  Known bugs / limitations.
 *****************************************************************************/
Ancestors and length with iterables are quadratic.
It doesn't always pull the synset we are looking for in outcasts, 
	sometimes it picks a different synset with similar name.

/******************************************************************************
 *  Describe whatever help (if any) that you received.
 *  Don't include readings, lectures, and precepts, but do
 *  include any help from people (including course staff, lab TAs,
 *  classmates, and friends) and attribute them by name.
 *****************************************************************************/


/******************************************************************************
 *  Describe any serious problems you encountered.                    
 *****************************************************************************/


/******************************************************************************
 *  If you worked with a partner, assert below that you followed
 *  the protocol as described on the assignment page. Give one
 *  sentence explaining what each of you contributed.
 *****************************************************************************/

We worked together almost entirely, so we both added to each class.


/**********************************************************************
 *  Have you completed the mid-semester survey? If you haven't yet,
 *  please complete the brief mid-course survey at https://goo.gl/gB3Gzw
 * 
 ***********************************************************************/
I don't know what that is....?

/******************************************************************************
 *  List any other comments here. Feel free to provide any feedback   
 *  on how much you learned from doing the assignment, and whether    
 *  you enjoyed doing it.                                             
 *****************************************************************************/