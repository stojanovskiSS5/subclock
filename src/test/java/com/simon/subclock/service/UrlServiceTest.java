package com.simon.subclock.service;

import com.simon.subclock.model.CallbackDTO;
import com.simon.subclock.repository.UrlRepository;
import com.simon.subclock.service.validation.CallbackValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ValidationException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UrlServiceTest {

    @Mock private UrlRepository urlRepository;
    @Mock private CallbackValidator callbackValidator;
    @Mock private CallbackDTO callbackDTO;
    @InjectMocks private UrlService urlService;


    @Test
    void testRegisterSuccessfully(){
       doNothing().when(callbackValidator).validateFrequency(any());
       doReturn(callbackDTO).when(urlRepository).addUrl(callbackDTO);
       urlService.register(callbackDTO);
       verify(urlRepository, times(1)).addUrl(any());
       verify(callbackValidator, times(1)).validateFrequency(any());
    }

    @Test
    void testRegisterValidatorThrowsException(){
        doThrow(ValidationException.class).when(callbackValidator).validateFrequency(any());
        assertThrows(ValidationException.class, () -> urlService.register(callbackDTO));
        verify(callbackValidator, times(1)).validateFrequency(any());
        verify(urlRepository, times(0)).addUrl(callbackDTO);
    }

    @Test
    void testRegisterRepositoryThrowsException(){
        doNothing().when(callbackValidator).validateFrequency(any());
        doThrow(ResponseStatusException.class).when(urlRepository).addUrl(any());
        assertThrows(ResponseStatusException.class, () -> urlService.register(callbackDTO));
        verify(callbackValidator, times(1)).validateFrequency(any());
        verify(urlRepository, times(1)).addUrl(any());
    }


    @Test
    void testUpdateSuccessfully(){
        doNothing().when(callbackValidator).validateFrequency(any());
        doReturn(callbackDTO).when(urlRepository).updateUrl(callbackDTO);
        urlService.update(callbackDTO);
        verify(urlRepository, times(1)).updateUrl(any());
        verify(callbackValidator, times(1)).validateFrequency(any());
    }

    @Test
    void testUpdateValidatorThrowsException(){
        doThrow(ValidationException.class).when(callbackValidator).validateFrequency(any());
        assertThrows(ValidationException.class, () -> urlService.update(callbackDTO));
        verify(callbackValidator, times(1)).validateFrequency(any());
        verify(urlRepository, times(0)).updateUrl(callbackDTO);
    }

    @Test
    void testUpdateRepositoryThrowsException(){
        doNothing().when(callbackValidator).validateFrequency(any());
        doThrow(ResponseStatusException.class).when(urlRepository).updateUrl(any());
        assertThrows(ResponseStatusException.class, () -> urlService.update(callbackDTO));
        verify(callbackValidator, times(1)).validateFrequency(any());
        verify(urlRepository, times(1)).updateUrl(any());
    }

    @Test
    void testDeregisterSuccessfully(){
        doReturn(callbackDTO).when(urlRepository).removeUrl(callbackDTO);
        urlService.deregister(callbackDTO);
        verify(urlRepository, times(1)).removeUrl(any());
    }

    @Test
    void testDeregisterRepositoryThrowsException(){
        doThrow(ResponseStatusException.class).when(urlRepository).removeUrl(any());
        assertThrows(ResponseStatusException.class, () -> urlService.deregister(callbackDTO));
        verify(urlRepository, times(1)).removeUrl(any());
    }

}
