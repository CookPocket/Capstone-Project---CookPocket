Capstone Project Bangkit Academy CookPocket (C242-PS383)
==
Overview
--
![image alt](https://github.com/CookPocket/Capstone-Project---CookPocket/blob/3cd46c1ebe9f3f6ec071ff98e60c4556769cea95/logoCookPocket.png)
<p align="justify">"CookPocket" is a machine learning-based mobile application designed to provide a smart solution in finding Indonesian recipes. The application aims to help users explore various traditional and modern dishes that suit the ingredients they have or their preferences. With the support of an artificial intelligence-based recommendation system, "CookPocket" is able to connect users with relevant recipes, provide a more personalized cooking experience, and encourage the preservation of the distinctive taste of Indonesian cuisine.</p>

Team Members
--
<!--

**Here are some ideas to get you started:**

🙋‍♀️ A short introduction - what is your organization all about?
🌈 Contribution guidelines - how can the community get involved?
👩‍💻 Useful resources - where can the community find your docs? Is there anything else the community should know?
🍿 Fun facts - what does your team eat for breakfast?
🧙 Remember, you can do mighty things with the power of [Markdown](https://docs.github.com/github/writing-on-github/getting-started-with-writing-and-formatting-on-github/basic-writing-and-formatting-syntax)
-->
Hi everyone! We are from C242-PS383. We consist of 7 members including:
| Bangkit ID | Name     | Learning Path | University  |LinkedIn |
| ---        | ---      | ---           | ---         | ---       |
| C803B4KY3361 | Nico Ramdani | Cloud Computing | Universitas Indonesia Membangun | [![text](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/nico-ramdani-4184a1232?utm_source=share&utm_campaign=share_via&utm_content=profile&utm_medium=android_app) |
| C117B4KY2996 | Muhammad Rafi Yasir | Cloud Computing | Intitut Teknologi Nasional Bandung | [![text](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](www.linkedin.com/in/rafi-yasir-ba0b17251) |
| A272B4KY0817 | Baso Ummul Ikshan | Mobile Development | Universitas Muslim Indonesia | [![text](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/baso-ummul-ikshan-380177281) |
| A272B4KY3129 | Muhammad Zaqly Luluang  | Mobile Development | Universitas Muslim Indonesia | [![text](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/muhammad-zaqly-luluang-468a61327/) |
| M117B4KY2947 | Muhammad Muslih Attoyibi | Machine Learning | Intitut Teknologi Nasional Bandung | [![text](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/muhammad-muslih-2153282b2/) |
| M595B4KY4484 | Wildan Septian | Mahine Learning | Universitas Sangga Buana YPKP | [![text](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](www.linkedin.com/in/wildan-septian-2109ahz) |
| M595B4KY3913 | Rizki Kurnia | Mahine Learning | Universitas Sangga Buana YPKP | [![text](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/rizki-kurnia-337287295/) |

### Documentation 

### Steps to Replicate

These are the replication steps:

#### Step 1: Clone the repository

```
git clone https://github.com/CookPocket/Capstone-Project---CookPocket.git
cd Capstone-Project---CookPocket
cd Cloud Computing
```

#### Step 2: Install all the required libraries

```
npm install bcryptjs dotenv express jsonwebtoken socket.io mysql2 nodemon
```

#### Step 3: Create the Database

To create the database, follow these steps:

1. Download the [db_cookpocket.sql](https://github.com/CookPocket/Capstone-Project---CookPocket/blob/main/Cloud%20Computing/db_cookpocket.sql) file from the Cloud Computing directory.
2. Execute the SQL commands in the downloaded `db_cookpocket.sql` file to set up the necessary database structure.

#### Step 4: Configure the .env file

- Rename the env.example file to .env

- Edit the content of .env according to your configuration, for example:

```
PORT=3306
DB_HOST=localhost
DB_USERNAME=your_username
DB_PASSWORD=your_password
DB_NAME=db_cookpocket
ACCESS_TOKEN_SECRET=your_access_token_secret
```

#### Step 5: Run the application

```
npm start
```

Open your browser and visit http://localhost:4000 in the terminal. If everything runs smoothly, you have successfully replicated this application.

#### Step 6: Test the API

It is recommended to use Postman for testing. For documentation, refer to the instructions above.
