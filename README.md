# Java Flat file Parser

This code uses the $HOMEPATH environment variable to seek input files to process. The input files must be at <code>$HOMEPATH/data/in/</code>, and the results report will be written at <code>$HOMEPATH/data/out/</code> folder. It also watches the input files folder for additions or changes and processes the new/changed files. 

##File format
Only .dat files will be analyzed. the valid fields are

###Salesman data

<code>001çCPFçNameçSalary</code>

###Customer data

<code>002çCNPJçNameçBusinessArea</code>

###Sales data

Inside the sales row, there is the list of items, which is
wrapped by square brackets [].

<code>003çSaleIDç[ItemID-ItemQuantity-ItemPrice]çSalesmanname</code>

##Running

To run the parser execute at the root:

<code>"mvn exec:java -Dexec.mainClass="com.gabrielcw.Main"</code>
