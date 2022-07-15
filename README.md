# kata-java-bankAccount (HEXAGONAL ARCHITECHTURE)

In this projet I used the HEXAGONAL ARCHITECHTURE. I used H2 database

# HOW TO TEST THESE APIs

1-) FREE THE PORT 8080 OF YOUR MACHINE

2-) LAUNCH APP AS ANY SPRING BOOT(MAVEN APP) in your local this is your BASE_URL = http://localhost:8080

# 3-) FIRST API: allows you to create an account

#  URL  : BASE_URL/api/accounts

#  VERB : POST
  
#  PAYLOAD: json content example: { "infoAccount":"Wamba" }
  
  RETURN VALUE: This can return null is case the creation failled(For security we don't provide reason) or This will create an account and return you 
  
  {
    "id": 3,
    "infoAccount": "wamba",
    "balance": 0
  }
  
# 4-) SECOND API: allows you to do a deposit
  
#  URL  : BASE_URL/api/accounts/idAccount/operations . Where  idAccount is a Long representing your account id
  
#  VERB : POST
  
#  PAYLOAD: json content example: { "amount": 10, "type":"DEPOSIT" } OR { "amount": 10, "type":"WITHDRAWAL" }
  
  RETURN VALUE: This can return null is case the operation failled(For security we don't provide reason) or this will return you json es:
  
  {
    "type": "DEPOSIT",
    "date": "2022-07-15T04:49:59.8216724",
    "amount": 10,
    "balance": 10
  } 
 
 OR 
 
 {
    "type": "WITHDRAWAL",
    "date": "2022-07-15T04:58:49.6532413",
    "amount": 10,
    "balance": 0
  }

# 4-) THIRD API: allows you to do a deposit

#  URL  : BASE_URL/api/accounts/idAccount/operations . Where  idAccount is a Long representing your account id
 
#  VERB : GET
  
  RETURN VALUE: This return a List of all your operations sorted in ASC order based on the date of the operation.


[
    {
        "type": "DEPOSIT",
        "date": "2022-07-15T05:06:44.005369",
        "amount": 10,
        "balance": 10
    },
    {
        "type": "DEPOSIT",
        "date": "2022-07-15T05:06:45.54522",
        "amount": 10,
        "balance": 20
    }
 ]



