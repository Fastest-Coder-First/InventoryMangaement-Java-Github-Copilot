
# InventoryMangaement-Java-Github-Copilot

DEMO OF THE PRODUCT:

URL:https://drive.google.com/file/d/1mt0NhmguKVyhJ9vwqvTT9xXlB2Ufae1J/view

SNAPSHOTS OF GITHUB-CO-PILOT-USAGE:

![image](https://github.com/Fastest-Coder-First/InventoryMangaement-Java-Github-Copilot/assets/31736263/1367e2d2-29bd-4e37-b815-0953645c545a)

![image](https://github.com/Fastest-Coder-First/InventoryMangaement-Java-Github-Copilot/assets/31736263/fd435063-1e3b-448d-914b-b85f3df89649)

![image](https://github.com/Fastest-Coder-First/InventoryMangaement-Java-Github-Copilot/assets/31736263/e580c642-e891-4dd8-9e77-d1c955c71159)

![image](https://github.com/Fastest-Coder-First/InventoryMangaement-Java-Github-Copilot/assets/31736263/4b23ddfe-6088-471d-b8b5-0bf4195187da)



DB SCHEMA MONGO:
{
        "_id" : "REALME9PRO",
        
        "productName" : "Realme",
        
        "description" : "it is nice phone",
        
        "manufacture" : "Realme",
        
        "category" : "Electronics",
        
        "manufactureDate" : "2022-09-08",
        
        "sellers" : [
                {
                        "_id" : "SELLER59",
                        
                        "variants" : [
                                
                                {
                                        "_id" : "REDREALMEPRO",
                                        
                                        "type" : "phone",
                                        
                                        "size" : "12inch",
                                        
                                        "color" : "RED",
                                        
                                        "highlights" : [
                                                
                                                {
                                                        "specifications" : {
                                        
                                                                "RAM" : "4GB"
                                                        
                                                        }
                                                }
                                        ],
                                        
                                        "stock" : 8,
                                        
                                        "cost" : 40000
                                
                                }
                        
                        ]
                
                },
                
                {
                        "_id" : "SELLER99",
                        
                        "variants" : [
                                {
                                      
                                        "_id" : "PINKREALMEPRO",
                                        
                                        "type" : "phone",
                                        
                                        "size" : "12inch",
                                        
                                        "color" : "PINK",
                                        
                                        "highlights" : [
                                          
                                                {
                                                
                                                        "specifications" : {
                                                        
                                                                "RAM" : "4GB"
                                                        
                                                        }
                                                
                                                }
                                        
                                        ],
                                        
                                        "stock" : 8,
                                        
                                        "cost" : 40000
                                
                                }
                        ]
                }
        ],
        "updated" : ISODate("2023-06-24T11:13:13.849Z"),
        "_class" : "com.ivs.cud.product.ms.models.ProductModel"
}


USES OF THE GITHUB-PILOT:

SHOW THE BUGS AND ALSO FIXES

SHOWS THE SYNTAX ERROR

SHOW COMMONLY USED SENTENCES

SHOW CODE IN ADVANCE


STEPS TO RUN:

STEP-1:
IMPORT THE BOTH MS IN TO VSCODE

STEP-2
START BOTH THE MICROSERVICES

STEP-3:
IMPORT THE Faster-code-microsoft.postman_collection in to POSTMAN FROM TESTING


Technologies:

Spring Boot

Spring web flux

Mongo db

Database : Mongo

Java Version : 17

Build : Maven

Design Pattern : Model-View-Controller

Server : Netty

Architecture : Microservices

ArchitectureType : RESTFUL

Programming: Reactive

Why Microsevice:

Future Scope

Integrate easily

Independant

Why Reactive

Overcomes the limitations of the Thread-per request model


Project :

InventoryMangaement-Java-Github-Copilot has two Microservices
1)ivs-vendor-product-ms

Operations:
Create a product

![image](https://github.com/Fastest-Coder-First/InventoryMangaement-Java-Github-Copilot/assets/31736263/f5d6f643-9c4a-45c6-968d-6897b3684d1b)

Exception Handling:
![image](https://github.com/Fastest-Coder-First/InventoryMangaement-Java-Github-Copilot/assets/31736263/fd880629-68ae-4fcb-8397-8d960ce58f94)

Get all products of a specific Seller
![image](https://github.com/Fastest-Coder-First/InventoryMangaement-Java-Github-Copilot/assets/31736263/7cbc0e63-5afa-4f47-a657-d0ad9a66d892)

Get Variant of Product:

![image](https://github.com/Fastest-Coder-First/InventoryMangaement-Java-Github-Copilot/assets/31736263/9785aaaf-adc6-4da1-afc2-4587533b29bb)

adding new seller to product with existing sellers

![image](https://github.com/Fastest-Coder-First/InventoryMangaement-Java-Github-Copilot/assets/31736263/49d18bbc-b9e7-4c58-a473-52a9b3c3b9eb)

adding new variants to existing sellers
![image](https://github.com/Fastest-Coder-First/InventoryMangaement-Java-Github-Copilot/assets/31736263/379393d7-2dea-468d-ba5d-18d86884a63b)

Update the stock for the product

![image](https://github.com/Fastest-Coder-First/InventoryMangaement-Java-Github-Copilot/assets/31736263/990cf31a-9ab7-4b6c-a69e-03b6bbf43b3f)

Delete Specific Variant
![image](https://github.com/Fastest-Coder-First/InventoryMangaement-Java-Github-Copilot/assets/31736263/4d463063-711c-4800-8fc5-f734fd898166)


2)ivs-file-upload

Operations:

CSV File Upload
![image](https://github.com/Fastest-Coder-First/InventoryMangaement-Java-Github-Copilot/assets/31736263/ad424402-b297-4a35-840c-b590cfaa592d)

Image File Upload
![image](https://github.com/Fastest-Coder-First/InventoryMangaement-Java-Github-Copilot/assets/31736263/6d311786-a1c0-4fa7-9614-ea350842b309)



