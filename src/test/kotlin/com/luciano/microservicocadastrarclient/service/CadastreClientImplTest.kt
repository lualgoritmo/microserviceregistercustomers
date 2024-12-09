import com.luciano.microservicocadastrarclient.datamodel.dateFormatter
import com.luciano.microservicocadastrarclient.model.AddressGeneric
import com.luciano.microservicocadastrarclient.model.ClientUser
import com.luciano.microservicocadastrarclient.output.gateway.CadastreClientImpl
import com.luciano.microservicocadastrarclient.output.gateway.ViaCepServiceImpl
import com.luciano.microservicocadastrarclient.repository.AddressRepository
import com.luciano.microservicocadastrarclient.repository.ClientUserRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.jupiter.MockitoExtension
import org.mockito.kotlin.any
import org.mockito.kotlin.whenever
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@ExtendWith(MockitoExtension::class)
class CadastreClientImplTest {

    @Mock
    lateinit var clientRepository: ClientUserRepository

    @Mock
    lateinit var addressRepository: AddressRepository

    @Mock
    lateinit var viaCep: ViaCepServiceImpl

    private lateinit var clientUserService: CadastreClientImpl

    private lateinit var client: ClientUser
    private lateinit var address: AddressGeneric

    @BeforeEach
    fun setUp() {

        clientUserService = CadastreClientImpl(clientRepository, viaCep, addressRepository)


        client = ClientUser(
            idClientUser = UUID.randomUUID(),
            nameSurname = "Terceiro Teste",
            cpf = "12345678901",
            cep = "17201110",
            dateOfBirth = LocalDate.parse("01-10-1983", dateFormatter),
            registrationDate = LocalDateTime.now(),
            numberResidence = "51",
            phone = "1234567890",
            rg = "1234567",
            email = "johndoe@example.com",
            addressClient = mutableSetOf()
        )

        address = AddressGeneric(
            idAddress = UUID.randomUUID(),
            cep = "17201110",
            road = "Rua Soldado Júlio Pinheiro de Araújo",
            city = "Jaú",
            numberResidence = "51",
            complement = "",
            uf = "SP",
            client = client
        )

    }
    @Test
    fun `when cadastreClient is called, it should return created client with address`() {


        whenever(clientRepository.save(client)).thenReturn(client)
        whenever(addressRepository.save(address)).thenReturn(address)
        whenever(viaCep.getAddressClient("17201110", client, "51")).thenReturn(address)
        val result = clientUserService.cadastreClient(client)

        assertNotNull(result)
        assertEquals(client.cep, result.cep)
        assertTrue(result.addressClient!!.isNotEmpty())
        assertEquals(1, result.addressClient?.size)

        val savedAddress = result.addressClient?.first()
        assertEquals(address.cep, savedAddress?.cep)
        assertEquals(address.city, savedAddress?.city)
        assertEquals(address.uf, savedAddress?.uf)
        assertEquals(client, savedAddress?.client)

        verify(clientRepository, times(1)).save(any())
        verify(addressRepository, times(1)).save(any())
    }
}
