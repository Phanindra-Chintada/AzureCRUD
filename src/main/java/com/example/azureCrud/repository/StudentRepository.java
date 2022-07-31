package com.example.azureCrud.repository;

import com.azure.cosmos.CosmosAsyncClient;
import com.azure.cosmos.CosmosAsyncContainer;
import com.azure.cosmos.CosmosClientBuilder;
import com.azure.cosmos.models.CosmosItemRequestOptions;
import com.azure.cosmos.models.PartitionKey;
import com.example.azureCrud.model.StudentModel;
import com.example.azureCrud.properties.CosmosProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentRepository {

    @Autowired
    CosmosProperties cosmosProperties;

    private PartitionKey partitionKey = new PartitionKey("STUDENT");

    public CosmosAsyncClient getCosmosClient(){
        CosmosAsyncClient cosmosAsyncClient = new CosmosClientBuilder()
                .endpoint(cosmosProperties.getUri())
                .key(cosmosProperties.getKey())//authenticationKey
                .contentResponseOnWriteEnabled(true)
                .buildAsyncClient();
        return cosmosAsyncClient;
    }

    public CosmosAsyncContainer getCosmosContainer(){
        CosmosAsyncContainer cosmosContainer = getCosmosClient()
                .getDatabase(cosmosProperties.getDatabase())
                .getContainer(cosmosProperties.getContainer());

        return cosmosContainer;
    }

    public StudentModel getStudentUsingId(String id){
        return getCosmosContainer()
                .readItem(id,partitionKey,StudentModel.class)
                .block()
                .getItem();
    }

    public StudentModel updateStudent(StudentModel student){
        if(student.getDocType()==null){
         student.setDocType("STUDENT");
        }
        return getCosmosContainer()
                .upsertItem(student)
                .block()
                .getItem();
    }

    public void deleteStudent(StudentModel student){
        getCosmosContainer()
                .deleteItem(student,new CosmosItemRequestOptions()).block().getItem();
    }

    public List<StudentModel> getAllStudents(){
        return getCosmosContainer().readAllItems(partitionKey,StudentModel.class).collectList().block();
    }
}
