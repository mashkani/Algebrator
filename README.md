# My Personal Project

## ALGEBRATOR

I am an undergraduate student at UBC studying Economics and Math. Similar to most math undergrads I have dealt
with different algebraic structures throughout my degree such as Vector Spaces, Groups, Rings and Fields. 
Consequently, I have found myself searching for some sort of application that can help me confirm if some set
paired with one or more operations is indeed an algebraic structure. Unfortunately, the search was never
successful. This has left me eager to design an application that contains similar functionality. For feasibility 
reasons I will only consider finite sets. The operations I will consider are modular multiplication and modular
addition. Furthermore, I will begin by designing a **Finite Group Calculator** and if time permits I will add a
**Finite Ring Calculator** and **Finite Field Calculator** to complete the Algebra Calculator which I will call 
the **Algebrator**. 

To demonstrate what the **Finite Group Calculator** will do, we must first know what a group is.
Below is the definition of a group given by https://proofwiki.org/wiki/Definition:Group_Axioms.

A group is an algebraic structure (G,∘) which satisfies the following four conditions:

-    	  	Closure   	    ∀a,b∈G:	a∘b∈G   		 		         
-   	  	Associativity   ∀a,b,c∈G:	a∘(b∘c)=(a∘b)∘c    				         
-   	  	Identity   	    ∃e∈G:∀a∈G:	e∘a=a=a∘e   	 			         
-   	  	Inverse   	    ∀a∈G:∃b∈G:	a∘b=e=b∘a   

Namely, a group is simply a set `G` paired with some operation `∘` that satisfies the four axioms above.
A finite group is simply a group that has a finite number of elements. 

Our **Finite Group Calculator** will take in a finite set and an operation, and it should check the four axioms.
If all axioms hold, then we have a group, otherwise we do not have a group.
The **Finite Group Calculator** can also return the identity element if it exists.
In addition, the **Finite Group Calculator** can return the inverse of each element in the group.  

If time permits, I can incorporate similar functionality for **Finite Rings** and **Finite Fields** as well. 

Anyone that deals with algebraic structures can benefit from this application.  


#### USER STORIES:

    -1 As a user I want to be able to add congruence classes to a set.
    -2 As a user I want to be able to pick my operation (either addition or multiplication).
    -3 As a user I want to be able to redefine my set.
    -4 As a user I want to be able to change my operation.
    
    -5 As a user I want to be able to save my defined set to file. 
    -6 As a user, when I start the application, I want to be given the option to load my defined set from file. 
    -7 As a user I want to be able to save my choosen operation to file. 
    -8 As a user, when I start the application, I want to be given the option to load my choosen operation from file.
    
    Phase 4: task 2
    The finiteGroup class has been made robust. 
    This has been accomplished by making the constructor throw a InvalidParameterException
    
    Phase 4: Task 3
    I would make CongruenceClass be a subtype of a newly added Element class.
    This would make any future changes/ upgrades a lot easier

###### main functionality that will be implemented in later phases:  

- As a user, I want to be able to determine if my set paired with the operation + or * is a Finite Group
    - As a user, I want to be able to determine if the group is abelian 
    - As a user, I want to be able to find the identity element of the group 
    - As a user, I want to be able to find the inverse of any element in the group