package org.isd.bankgrpcservice.grpc.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import org.isd.bankgrpcservice.grpc.stub.Bank;
import org.isd.bankgrpcservice.grpc.stub.BankServiceGrpc;

import java.io.IOException;

public class GrpcClient {
    public static void main(String[] args) throws IOException {
        ManagedChannel managedChannel= ManagedChannelBuilder.forAddress("localhost",9999)
                .usePlaintext()
                .build();
        BankServiceGrpc.BankServiceBlockingStub blockingStub= BankServiceGrpc.newBlockingStub(managedChannel);
        Bank.ConvertCurrencyRequest.Builder builder = Bank.ConvertCurrencyRequest.newBuilder();
        builder.setCurrencyFrom("USD");
        builder.setCurrencyTo("MAD");
        builder.setAmount(888000);
        Bank.ConvertCurrencyRequest currencyRequest = builder.build();
        Bank.ConvertCurrencyResponse convertCurrencyResponse = blockingStub.convertCurrency(currencyRequest);
        System.out.println("*************************");
        System.out.println(String.format("%f en %s => %f en %s",
                convertCurrencyResponse.getAmount(),convertCurrencyResponse.getCurrencyFrom(),
                convertCurrencyResponse.getConversionResult(),convertCurrencyResponse.getCurrencyTo()));
        System.out.println("************************");


    }
}
