# Shelter brain

**Web app**, which manages animals' shelter.

**Features**:
* Adding animals by shelter's workers
* Deleting animals by workers
* Generating shelter reports
* Fetching shelter reports in pdf and csv formats
* Registration and logging in as worker
* Automatically sending email warning to workers about shelter state if there is <= 50% of free space
* Storing images of animals
* Storing profile photos of workers
* Deployment on heroku
* Mobile app which displays shelter's report (React Native)
* Web app which makes use of all backend's features

## Tech stack

**The most important technologies**:
1. Java (8+)
2. JavaScript (ES6+)
3. Spring (MVC, Data JPA, Boot, Mail)
4. Hibernate
5. React (with create-react-app)
6. Lombok
7. Maven
8. PostgreSQL
9. H2
10. OpenPDF
11. Apache Commons CSV
12. JWT Java library
13. yarn
14. axios
15. Bootstrap 4 (and reactstrap)
16. HTML5
17. React Router
18. React Native
19. Heroku
20. Git (hosted on GitHub)

## TODO List  

----

## Developer's info

Frontend and backend are totally separeted (unique directories and code bases)  

__Running backend__:  
&nbsp;&nbsp;&nbsp;&nbsp;Run main() method of ShelterBrainBackendApplication class.  

__Running frontend__:  
&nbsp;&nbsp;&nbsp;&nbsp;Enter frontend directory and call 'yarn start'.  

__Running mobile app__:  
&nbsp;&nbsp;&nbsp;&nbsp;Enter mobile_app directory and call 'yarn start'.   Now scan QR code which you can find at new browser card generated by Expo.  

## Deployment  
#### You can deploy application (backend) on heroku.  
```
	heroku login  
	cd backend  
	heroku create  
	git add .
	git commit -m "."  
	git push heroku master  
	heroku addons:create heroku-postgresql  
```

&nbsp;&nbsp;&nbsp;&nbsp;In backend directory we have second - *nested git repository*, because heroku needs it - this repository is only local, without connected remote.  
&nbsp;&nbsp;&nbsp;&nbsp;**Database URI** is in heroku config. You must copy&paste it to application.properties.  
&nbsp;&nbsp;&nbsp;&nbsp;Now swap the backend URL at frontend side in **Config.js** file.  
&nbsp;&nbsp;&nbsp;&nbsp;*heroku logs --tail* - displaying backend's logs in real time  
  
#### Updating an existing backend instance on heroku:  
```
	cd backend  
	git add .  
	git commit -m "."  
	git push heroku master  
```

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;If you have whole application connected to some git repository  

```
	cd ..  
	git add .  
	git commit -m "changes description"  
	git push origin master  
```
