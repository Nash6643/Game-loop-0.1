Java 2D Game Engine (Game-loop-0.1)
A modular, lightweight 2D game engine built in Java using Swing/AWT. Designed with a clean package architecture separating core game loops, decoupled physics events, configurable audio listeners, state-driven entities, and tilemap serialization.

Architecture Overview
The repository structure reflects a decoupled, component-based subsystem design:

engine/
├── audio/       # Event-driven sound listeners & playback sub-systems
├── config/      # EngineConfig property loader for runtime persistence
├── core/        # Core engine thread lifecycle & system metrics
├── entity/      # State-driven actor behaviors & EntityState logic
├── fx/          # ParticleEmitter management, batching, & lifecycle disposal
├── graphics/    # 2D Camera viewport with lerp-based target tracking
├── input/       # Key/Mouse input bindings & event dispatchers
├── physics/     # AABB collision engine & decoupled CollisionEvent containers
├── scene/       # Scene state management & TileMap world integration
├── tile/        # MapSerializer helper for CSV level data import/export
└── ui/          # Interactive DebugOverlay & runtime control widgets
Key Features
🎮 Core & Architecture
Decoupled Engine Subsystems: Dedicated packages for graphics, input, audio, and physics to ensure clean separation of concerns.

Persistent Configuration: Built-in EngineConfig loader for managing runtime engine settings and properties seamlessly.

🕹️ Entities & State Management
State-Driven Actors: Flexible EntityState enumeration for dynamic entity behaviors and state machine transitions.

Scene Graph: Modular TestScene and scene management layer supporting active object lifecycles and tilemap integration.

⚡ Physics & Collision Systems
AABB Collision Engine: Axis-Aligned Bounding Box collision detection with trigger boundaries and static obstacles.

Event-Driven Mechanics: Decoupled CollisionEvent containers and listeners for clean, reactive physics callbacks.

🎥 Camera & Visual Effects
Smooth Camera Tracking: 2D camera viewport utilizing linear interpolation (lerp) for smooth focus and target tracking.

Particle Emitter Engine: Dynamic ParticleEmitter system featuring particle batching, lifetime decay, and automated memory disposal.

🗺️ Tilemap & Level Design
CSV Level Serialization: Integrated MapSerializer to import and export level designs via simple CSV formats.

Layered Rendering: Multi-layer tile rendering integrated directly into scene management.

🛠️ UI & Debugging Tools
Interactive Debug Overlay: In-game debug suite featuring real-time metric tracking, audio toggles, and visual bounding box overlays.

Getting Started
Prerequisites
Java Development Kit (JDK): 11 or higher recommended.

IDE: IntelliJ IDEA, Eclipse, or VS Code (optional, but recommended).

Installation & Run
Clone the Repository

Bash
git clone https://github.com/Nash6643/Game-loop-0.1.git
cd Game-loop-0.1
Compile the Project

Bash
javac -d bin $(find engine -name "*.java")
Run the Engine Target

Bash
java -cp bin engine.core.Main
(Replace engine.core.Main with your specific entry point class if applicable).

CSV Level Format Example
The tile subsystem supports importing custom map grids via standard CSV files:

Code snippet
1,1,1,1,1
1,0,0,0,1
1,0,2,0,1
1,1,1,1,1
0: Empty / Walkable space

1: Wall / Solid collidable tile

2: Trigger / Event tile
