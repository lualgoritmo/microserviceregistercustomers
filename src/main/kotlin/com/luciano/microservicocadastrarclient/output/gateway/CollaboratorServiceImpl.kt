package com.luciano.microservicocadastrarclient.output.gateway

import com.luciano.microservicocadastrarclient.model.Collaborator
import com.luciano.microservicocadastrarclient.repository.AddressRepository
import com.luciano.microservicocadastrarclient.repository.CollaboratorRepository
import com.luciano.microservicocadastrarclient.service.service.CollaboratorService
import jakarta.transaction.Transactional
import javassist.NotFoundException
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class CollaboratorServiceImpl(
    private val collaboratorRepository: CollaboratorRepository,
    private val viaCepServiceImpl: ViaCepServiceImpl
): CollaboratorService {
    override fun createCollaborator(collaborator: Collaborator): Collaborator {
        val addressCollaborator = viaCepServiceImpl.getAddress(
            cep = collaborator.cep,
            collaborator = collaborator,
            numberResidence = collaborator.numberResidence?:""
        )
        collaborator.addressCollaborator.add(addressCollaborator)
        return collaboratorRepository.save(collaborator)

    }
    override fun getCollaboratorWithId(idCollaborator: UUID): Collaborator {
        return collaboratorRepository.findById(idCollaborator).orElseThrow {
            RuntimeException("Este usuário não existe!")
        }
    }

    override fun findAllById(idsCollaborator: List<UUID>): List<Collaborator> {
        if(idsCollaborator.isEmpty()) {
            return emptyList<Collaborator>().apply {
                println("Lista vazia no serviço!")
            }
        }
        return collaboratorRepository.findAllById(idsCollaborator)
    }
    @Transactional
    override fun findByIdCollaborator(idCollaborator: UUID): Collaborator =
        collaboratorRepository.findById(idCollaborator).orElseThrow {
          NotFoundException("O Colaborador não existe!")
       }

}