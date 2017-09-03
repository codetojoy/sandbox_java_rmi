
### sandbox_java_rmi

Example of Java RMI.

### To Run

* The order of operations matters in the steps below!

* in window 1: `gradle :UserService:run`
* in window 2: `gradle :BillingService:run`
* in window 3: `gradle :CompoundService:run`
* in window 4: `./run_client.sh`

### Commands

* `u` to use UserService
* `b` to use BillingService
* `c` to use CompoundService
* `r` to list registry
* `q` to quit

### Notes

* `client` must be run _in situ_ because it uses `stdin`
