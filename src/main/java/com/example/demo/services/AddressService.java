package com.example.demo.services;

import com.example.demo.dto.AddressDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AddressService {

    private final ConcurrentHashMap<Long, AddressDto> addressStore = new ConcurrentHashMap<>();
    private long currentId = 1;

    public List<AddressDto> findAll() {
        return new ArrayList<>(addressStore.values());
    }

    public Optional<AddressDto> findById(Long id) {
        return Optional.ofNullable(addressStore.get(id));
    }

    public AddressDto addAddress(AddressDto addressDto) {
        addressDto.setId(currentId++);
        addressStore.put(addressDto.getId(), addressDto);
        return addressDto;
    }

    public Optional<AddressDto> updateAddress(Long id, AddressDto addressDto) {
        if (addressStore.containsKey(id)) {
            addressDto.setId(id);
            addressStore.put(id, addressDto);
            return Optional.of(addressDto);
        }
        return Optional.empty();
    }

    public boolean deleteAddress(Long id) {
        return addressStore.remove(id) != null;
    }
}

