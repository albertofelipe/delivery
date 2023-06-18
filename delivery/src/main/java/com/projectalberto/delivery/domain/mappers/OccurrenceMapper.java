package com.projectalberto.delivery.domain.mappers;

import com.projectalberto.delivery.domain.dto.OccurrenceDTO;
import com.projectalberto.delivery.domain.model.Occurrence;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class OccurrenceMapper {

    private ModelMapper mapper;

    public OccurrenceDTO toDTO(Occurrence occurrence){
        return mapper.map(occurrence, OccurrenceDTO.class);
    }

    public List<OccurrenceDTO> toCollectionDTO(List<Occurrence> occurrences){
        return occurrences.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
