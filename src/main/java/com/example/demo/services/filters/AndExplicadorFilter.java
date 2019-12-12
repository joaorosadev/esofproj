package com.example.demo.services.filters;

import com.example.demo.models.Explicador;

import java.util.Set;

public class AndExplicadorFilter implements FilterExplicadorI {
    private FilterExplicadorI filter;
    private FilterExplicadorI otherFilter;

    public AndExplicadorFilter(FilterExplicadorI filter, FilterExplicadorI otherFilter) {
        this.filter = filter;
        this.otherFilter = otherFilter;
    }

    @Override
    public Set<Explicador> filter(Set<Explicador> entities) {
        Set<Explicador> filter1=this.filter.filter(entities);
        return this.otherFilter.filter(filter1);
    }
}
