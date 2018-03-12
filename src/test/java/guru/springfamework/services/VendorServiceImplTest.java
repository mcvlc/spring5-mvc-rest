package guru.springfamework.services;

import guru.springfamework.api.v1.mapper.VendorMapper;
import guru.springfamework.api.v1.model.VendorDTO;
import guru.springfamework.controllers.v1.VendorController;
import guru.springfamework.domain.Vendor;
import guru.springfamework.repositories.VendorRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class VendorServiceImplTest {


    @Mock
    VendorRepository vendorRepository;

    VendorMapper vendorMapper = VendorMapper.INSTANCE;

    VendorService vendorService;

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);

        vendorService = new VendorServiceImpl(vendorRepository, vendorMapper);
    }

    @Test
    public void getAllVendors() throws Exception{
        Vendor vendor1 = new Vendor();
        vendor1.setId(1L);
        vendor1.setName("Fruit");

        Vendor vendor2 = new Vendor();
        vendor1.setId(2L);
        vendor1.setName("Nuts");

        when(vendorRepository.findAll()).thenReturn(Arrays.asList(vendor1,vendor2));

        List<VendorDTO> vendorDTOS = vendorService.getAllVendors();

        assertEquals(2, vendorDTOS.size());
    }

    @Test
    public void getVendorById() throws Exception{
        Vendor vendor = new Vendor();
        vendor.setId(1L);
        vendor.setName("Fruit");

        when(vendorRepository.findById(anyLong())).thenReturn(java.util.Optional.ofNullable(vendor));

        VendorDTO vendorDTO = vendorService.getVendorById(1L);

        assertEquals("Fruit", vendorDTO.getName());
    }

    @Test
    public void createNewVendor() throws Exception{

        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("Fruit");

        Vendor savedVendor = new Vendor();
        savedVendor.setName(vendorDTO.getName());
        savedVendor.setId(1L);

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        VendorDTO savedDTO = vendorService.createNewVendor(vendorDTO);

        assertEquals(vendorDTO.getName(), savedDTO.getName());
        assertEquals(VendorController.BASE_URL + "/1", savedDTO.getVendorUrl());
    }

    @Test
    public void saveVendorByDTO() throws Exception{
        VendorDTO vendorDTO = new VendorDTO();
        vendorDTO.setName("Fruit");

        Vendor savedVendor = new Vendor();
        savedVendor.setId(1L);
        savedVendor.setName(vendorDTO.getName());

        when(vendorRepository.save(any(Vendor.class))).thenReturn(savedVendor);

        VendorDTO savedDTO = vendorService.saveVendorByDTO(1L, vendorDTO);

        assertEquals(vendorDTO.getName(), savedDTO.getName());
        assertEquals(VendorController.BASE_URL + "/1", savedDTO.getVendorUrl());
    }

    @Test
    public void deleteById() throws Exception{
        Long id = 1L;

        vendorRepository.deleteById(id);

        Mockito.verify(vendorRepository, times(1)).deleteById(anyLong());
    }
}
