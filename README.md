# CARE CENTRAL

## Database Design

- [ ] Create an ER diagram of the database
- [ ] Write Create table scripts ![script](/src/main/resources/db/migration/V1__create_products.sql)

![project ER diagram](https://iili.io/HZmLltV.jpg)[ER Diagram](path/to/ER/diagram)

## Project Setup

- [ ] Create a new Java project
- [ ] Set up a MySQL database
- [ ] Add necessary libraries
	- [ ] JDBC, 
	- [ ] MySQL Connector, 
	- [ ] JUnit, 
	- [ ] Dotenv

## Module: User 
### Feature: User creation
#### user story
- User can create their profile easily.
#### Pre-requisites:
- [ ] Create User table
- [ ] Implement User model
- [ ] Implement User DAO (create)

#### Validations:
 - [ ] Form validations
	- first name
	- Last name
	- Age
	- Email id 
	- Password
 - [ ] Business validations
	 - Check whether the email id already exists.

#### Messages: 
- [ ] Invalid Email Id
- [ ] Password doesn't match the required format
- [ ] first name cannot be null or empty
- [ ] last name cannot be null or empty
- [ ] mobile number does not match the required format
- [ ] User must be at least age of  17.
- [ ] User already exists (Business validation)


#### Flow: 
```mermaid
graph TD;
  A[User Service: Create User] --> B[Form Validation]
  B --Yes--> C[Business Validation]
  B--No -->C2[Throws validation exception]
  C -- Yes --> D[User Service: Email Already Exists ]
  D --> E[User Service: User Created ]
  C -- No --> F[User DAO: create User]

```
### Feature: Update User
#### User Story:
- User can update their profile details.
#### Pre-requisites:
- [ ] Implement user model 
- [ ] Implement user DAO (update)

#### Validations:
- [ ] Form validations
	- Validating the fields which the user is going to update(i.e. first name , last name , password , mobile number , age)
- [ ] Business validations
	- check whether the id exists.

#### Messages:
- [ ] Password doesn't match the required format
- [ ] first name cannot be null or empty
- [ ] last name cannot be null or empty
- [ ] mobile number does not match the required format
- [ ] User must be at least age of  17.
- [ ] Id cannot be negative.
- [ ] User not found
- [ ] User cannot be null or empty


#### Flow:
```mermaid
graph TD;
A[User Service: Update User using id] --> B[Form validation]
B--Yes-->C[Business validation]
B--No-->C2[Throws validation exception]
C -- Yes --> D[User DAO : Update User]
D --> E[User Service : User updated successfully]
C -- No --> F[User Service : User not found]
```

### Feature: Delete User
#### User Story:
- User can delete their account.
#### Pre-requisites:
- [ ] Implement user model
- [ ] Implement user DAO(delete)

#### Validations:
- [ ] Form validations
	- Check whether the id is valid or not
- [ ] Business validations
	- Check whether the id exists.

#### Messages:
- [ ] Invalid id 
- [ ] User not found

#### Flow:
```mermaid
graph TD;
A[User Service: Deleting User using id] -->B[Form validation]
B --Yes-->C[Business validation]
B--No-->C2[Throws validation exception]
C --Yes --> D[User DAO : Delete User]
D --> E[User Service : User deleted successfully]
C -- No --> F[User Service : User not found]
```
### Feature: List User
#### Pre-requisites:
- [ ] Implement user model
- [ ] Implement user DAO(findAll)

#### Validations:
- [ ] No validations

#### Flow:
```mermaid
graph TD;
A[User Service : getting all users without any arguments] -->C[User DAO: Find all]
C --> E[returning all active users]
``` 
## Module: Doctor
### Feature: Doctor creation
#### User Story:
- Admin can upload the doctor detail.
#### Pre-requisites:
- [ ] Create Doctor's record in user table (should complete)
- [ ] Create Doctors table
- [ ] Implement doctor model
- [ ] Implement doctor DAO(create)

#### Validations: 
- [ ] Form validations
	- First name
	- Last name
	- Age 
	- Mobile number
	- Email id
	- Password
	- Qualifications
	- Experience (whether it is in year or month)
	- Department
- [ ] Business validation
	- Existence of email
	
#### Messages:
- [ ] Email cannot be null or empty
- [ ] Email doesn't match the required format
- [ ] Password doesn't match the required format
- [ ] first name cannot be null or empty
- [ ] last name cannot be null or empty
- [ ] mobile number does not match the required format
- [ ] User must be at least age of  17.
- [ ] User already exists (Business validation)
- [ ] Experience must be in years.
- [ ] Qualifications must not be null or empty.
- [ ] Department must not be null or empty
- [ ] User cannot be null or empty

#### Flow:

```mermaid
graph TD;
A[User Service : Create] -->A1[Form validation]
A1--Yes-->B[Business Validation]
A1--No -->B2[Throws validation exception]
B -- Yes-->C[User Service : User already exists]
B -- No -->D[User DAO: Create]
D --> E[User Service : User created successfully]
E -->F[Doctor Service: Create ]
F-->G[Form validation]
G--Yes-->H[Business validation]
G--No -->H2[Throws validation exception]
H-- Yes --> I[Doctor DAO: Create Doctor]
I--> J[Doctor Service: Doctor created successfully]
H-- No --> K[Doctor already exists]
```

### Feature: Update Doctor
#### User Story:
- Doctors can update their profile.
#### Pre-requisites
- [ ] Implement Doctor model
- [ ] Implement Doctor DAO (update)

#### Validations
- [ ] Form validation
	- First name
	- Last name
	- Age 
	- Mobile number
	- Qualifications
	- Experience (whether it is in year or month)
	- Department
- [ ] Business validation
	- Existence of user id and doctor id

#### Messages:
- [ ] Password doesn't match the required format
- [ ] first name cannot be null or empty
- [ ] last name cannot be null or empty
- [ ] mobile number does not match the required format
- [ ] User must be at least age of  17.
- [ ] Experience must be in years.
- [ ] Qualifications must not be null or empty.
- [ ] Department must not be null or empty

#### Flow:
``` mermaid
graph TD;
A[User Service : Update] -->B[Form validation]
B--Yes-->C1[Business Validation]
B--No-->C2[Throws validation exception]
C1--Yes-->D[User DAO: Update user]
C1--No-->D2[User not found]
D-->E[User service : User updated successfully]
E-->F[Doctor Service : Update] --> G[Form validation]
G--Yes-->H[Business validation]
G--No-->H2[Throws  validation exception]
H-- Yes --> I[Doctor DAO : update]
 I--> J[Doctor Service : Doctor updated successfully]
 H-- No -->I2[Id not found to update]
```
### Feature: Delete
#### User Story:
- Doctors can delete their profile.
#### Pre-requisites:
- [ ] Implementing user model
- [ ] Implementing user DAO(delete)

#### Validations:
- [ ] Form validations
	- valid id or not 
- [ ] Business validations
	- existence of id

#### Flow:
``` mermaid
graph TD;
A[User Service:Delete Doctor] -->A1[Form validation]
A1--Yes --> B[business validation]
A1--No-->B2[throw validation exception]
B -- Yes -->C[User DAO : Delete doctor]
C -->D[User Service : Doctor deleted successfully]
B -- No -->E[Doctor not found to delete]
```

### Feature : Listing doctors
#### User Story:
- Users can see the list of Doctors.
#### Pre-requisites:
- [ ] Implement User Model
- [ ] Implement Doctor DAO
- [ ] Implement User DAO

#### Validations:
- [ ] No validations

#### Flow:
```mermaid
graph TD;
A[User Service : find all doctors] -->B[User DAO : find all doctors]
B-->C[returning all doctors which is active in the user table]
C -->D[Doctor Service : find all]
D -->E[Doctor DAO : find all doctors]
E -->F[returning all doctors]
```

### Feature: Find doctors by id
#### User Story:
- Users can see the particular doctor details.
#### Pre-requisites:
- [ ] Implement User model
- [ ] Implement User DAO
- [ ] Implement Doctor DAO
- [ ] Implement Doctor model

#### Validations:
- [ ] Form validations 
	- Check whether the id is valid or not 
- [ ] Business validations
	- Existence of id in the table

#### Messages:
- [ ] Id cannot be negative.
- [ ] Id not found

#### Flow:
```mermaid
graph TD;
A[Doctor Service : find by id] -->B[Form validation]
B--Yes-->C1[Business validation]
B--No-->C2[Throws validation exception]
C1--Yes-->D1[Doctor DAO : find by id]
D1-->E[Doctor Service : Return the doctor]
C1--No-->D2[throws validation exception]
E-->F[User Service : find by id]
F-->G[Form validation]
G--Yes-->H1[Business validation]
G--No-->H2[throws exception]
H1--Yes-->I1[User DAO : find by id]
H1--No-->I2[throws validation exception]
I1-->J[User Service : returns first name and last name]
```
