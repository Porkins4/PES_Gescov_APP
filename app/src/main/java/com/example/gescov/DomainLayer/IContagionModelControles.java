package com.example.gescov.DomainLayer;

import java.util.concurrent.ExecutionException;

public interface IContagionModelControles {
    String getAllContagions() throws ExecutionException, InterruptedException;
}
