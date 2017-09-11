
### sandbox_java_rmi

Example of Java RMI.

### To Run

* in window 0: `gradle :Registry:run`
    * this is a stand-alone which the services will use
* in window 1: `gradle :UserService:run`
* in window 2: `gradle :BillingService:run`
* in window 3: `gradle :CompoundService:run`
    * this one waits for `UserService` and `BillingService` to start
    * i.e. steps 1,2,3 can occur in any order
* in window 4: `./run_client.sh`
    * will handle errors if steps 0,1,2,3 aren't executed
* alternatively, in window 5: `gradle :Monitor:run`
    * will ping services

### Commands

* `u` to use UserService
* `b` to use BillingService
* `c` to use CompoundService
* `r` to list registry
* `q` to quit

### Notes

* `client` must be run _in situ_ because it uses `stdin`
