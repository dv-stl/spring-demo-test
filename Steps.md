# Agenda


## 1. @SpringBootTest 

Test com.example.simple.services.InputTransformerTest 

It shows that whole spring is started, classpath is scanned and beans are instantiated
Spring boot test brings many useful deps 


## 2. It is possible to limit what is imported 

Test com.example.simple.services.InputTransformerImportTest

It shows that whole spring is started, classpath is scanned and beans are instantiated
Spring boot test brings many useful deps. 


## 3. Now, slightly more complex case 
Run the slightly more complex app and use
curl  http://localhost:8080/api/v1/cars
or
```
curl -X POST http://localhost:8080/api/v1/cars \
-H "Content-Type: application/json" \
-d '{
"brand": "Toyota",
"engineCapacity": 1.8,
"productionYear": 2022
}'
```

## 4. Show the dealer test - isolated domain logic test

## 5. How to test web layer, with no Servlet Container running 
@WebMvcTest - example of a slice for web layer testing 
Provides mockMvc

## 6. Testing DB layer 
@DataJpaTest - example of a slice for DB testing, only Repositories are loaded 



