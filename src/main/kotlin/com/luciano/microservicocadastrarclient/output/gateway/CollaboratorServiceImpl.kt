package com.luciano.microservicocadastrarclient.output.gateway

import com.luciano.microservicocadastrarclient.model.Collaborator
import com.luciano.microservicocadastrarclient.repository.CollaboratorRepository
import com.luciano.microservicocadastrarclient.service.CollaboratorService
import jakarta.transaction.Transactional
import javassist.NotFoundException
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalTime
import java.util.UUID

@Service
class CollaboratorServiceImpl(
    private val collaboratorRepository: CollaboratorRepository,
    private val viaCepServiceImpl: ViaCepServiceImpl
): CollaboratorService {
    @Transactional
    override fun createCollaborator(collaborator: Collaborator): Collaborator {
        val addressCollaborator = viaCepServiceImpl.getAddress(
            cep = collaborator.cep,
            collaborator = collaborator,
            numberResidence = collaborator.numberResidence?:""
        )
        collaborator.addressCollaborator.add(addressCollaborator)
        return collaboratorRepository.save(collaborator)

    }
    @Transactional
    override fun getCollaboratorWithId(idCollaborator: UUID): Collaborator {
        return collaboratorRepository.findById(idCollaborator).orElseThrow {
            RuntimeException("Este usuário não existe!")
        }
    }
    @Transactional
    override fun findAllById(idsCollaborator: List<UUID>): List<Collaborator> {
        if(idsCollaborator.isEmpty()) {
            return emptyList<Collaborator>().also {
                println("Lista vazia no serviço!")
            }
        }
        return collaboratorRepository.findAllById(idsCollaborator).also {
            println("Retorno de lista com sucesso!")
        }
    }
    @Transactional
    override fun findAvailableCollaborators(serviceDate: LocalDate, serviceHours: LocalTime, pageAble: Pageable): List<Collaborator> {
        val pageable:PageRequest = PageRequest.of(0, 2)

        return collaboratorRepository.findAvailableCollaborators(
                serviceDate = serviceDate,
                serviceHours = serviceHours,
                pageable = pageable
        ).also {
                if(it.isNullOrEmpty()) {
                    println("Nenhum colaborador disponível para a data e horário fornecido")
                } else {
                    println("Colaboradores disponíveis encontrados")
                }
        }
    }
    @Transactional
    override fun findByIdCollaborator(idCollaborator: UUID): Collaborator =
        collaboratorRepository.findById(idCollaborator).orElseThrow {
          NotFoundException("O Colaborador não existe!")
       }

}
