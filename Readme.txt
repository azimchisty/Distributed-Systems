Both Client and Server directories contain run.sh file.
First run the file run.sh and give the port no. for RMI Registry.

For example:
bash run.sh 7827
Where, 7827 is the port no. on which RMI Registry has to be run.

Now, run run.sh file present in Client directory along with three
parameters as follows:
1) File name
2) Host Address
3) Port for RMI Registry

For example:
bash run filename 127.0.0.1 7827
Note: During run time both the port nos. must be same.