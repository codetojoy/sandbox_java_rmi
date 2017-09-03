
### sandbox_java_rmi

Example of Java RMI.

### To Run

* in window 1: `gradle :UserService:run`
* in window 2: `gradle :BillingService:run`
* in window 3: `gradle :CompoundService:run`
    * this one waits for `UserService` and `BillingService` to start
    * i.e. the above 3 steps can occur in any order
* in window 4: `./run_client.sh`
    * currently requires above 3 steps to run

### Commands

* `u` to use UserService
* `b` to use BillingService
* `c` to use CompoundService
* `r` to list registry
* `q` to quit

### Notes

* `client` must be run _in situ_ because it uses `stdin`
