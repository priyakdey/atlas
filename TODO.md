- [X] Setup repo for multigradle project
- [ ] Setup github ci for development and merge
- [ ] Setup codecov
- [ ] Setup synk
- [ ] Start with design of compass. API for registration of service.
- [ ] Internal memstore
- [ ] Ping Scheduler
- [ ] Compas should be Config driven

For later:

Atlas Service Mesh Ecosystem
Core Infrastructure
🏛️ atlas-registry - Service discovery and registration
📡 atlas-beacon - Service registration library
🧭 atlas-compass - Client-side load balancing
🌐 atlas-client - HTTP wrapper with mesh integration
Resilience & Protection
🛡️ atlas-shield - Circuit breaker (Hystrix-like)
🚦 atlas-throttle - Rate limiting and traffic control
💓 atlas-pulse - Health checks and monitoring
Observability & Management
🔍 atlas-trace - Distributed tracing (Jaeger/Zipkin-like)
⚙️ atlas-config - Configuration management
📊 atlas-insights - Metrics collection and analytics
Optional Advanced Components
🔐 atlas-vault - Service-to-service authentication/authorization
🚪 atlas-gateway - API gateway and ingress controller
📈 atlas-metrics - Prometheus-compatible metrics exporter

PLAN:

- Bootstrap Core Infrastructure
  - [ ] atlas-config – foundation: all other components use it
  - [ ] atlas-registry – basic discovery, enables distributed service coordination
- Enable Service Communication
  - [ ] atlas-beacon – register services with atlas-registry
  - [ ] atlas-compass – load balance across discovered instances
  - [ ] atlas-client – make HTTP calls with load balancing
- Resilience Layer
  - [ ] atlas-shield – wrap calls with circuit breakers
  - [ ] atlas-throttle – add rate limiting per endpoint/service
- Observability Layer
  - [ ] atlas-pulse – service health exposure and checks
  - [ ] atlas-trace – trace request flows
  - [ ] atlas-insights – gather and aggregate metrics
- Optional Advanced Features
  - [ ] atlas-vault – secure comms and auth between services 
  - [ ] atlas-gateway – route + secure external requests
  - [ ] atlas-metrics – export metrics to Prometheus/Grafana