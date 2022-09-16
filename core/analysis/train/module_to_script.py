import torch

def module_to_script(module_path, save_path):
    device = "cuda" if torch.cuda.is_available() else "cpu"
    module = torch.load(module_path, map_location=device)
    script = torch.jit.script(module)
    torch.jit.save(script, save_path)

if __name__ == "__main__":
    module_to_script("")